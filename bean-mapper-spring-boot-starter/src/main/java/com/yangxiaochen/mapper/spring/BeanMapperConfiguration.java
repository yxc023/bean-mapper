package com.yangxiaochen.mapper.spring;

import com.ke.commerce.shop.mapper.BeanMapper;
import com.ke.commerce.shop.mapper.OneWayMapping;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import lombok.val;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMapperConfiguration {

    @Bean
    public BeanMapper beanMapper() {
        BeanMapper beanMapper = new BeanMapper();
        try (ScanResult scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .enableClassInfo()
                .whitelistPackages("com.ke.commerce.shop")
                .scan()) {
            scanResult.getClassesWithAnnotation(BeanMapping.class.getName()).forEach(classInfo -> {
                Class mappingClass = classInfo.loadClass();
                if (!ClassUtils.isAssignable(mappingClass, OneWayMapping.class)) {
                    throw new IllegalArgumentException();
                }
                try {
                    val mapping = mappingClass.newInstance();
                    beanMapper.addMapping((OneWayMapping) mapping);
                } catch (Exception e) {
                    throw new IllegalArgumentException();
                }
            });

        }
        return beanMapper;
    }
}
