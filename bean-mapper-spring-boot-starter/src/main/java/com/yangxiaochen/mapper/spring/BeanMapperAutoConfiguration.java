package com.yangxiaochen.mapper.spring;


import com.yangxiaochen.mapper.BeanMapper;
import com.yangxiaochen.mapper.OneWayMapping;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BeanMapperProperties.class)
@ConditionalOnProperty("yxc.bean.mapper.scanPackage")
public class BeanMapperAutoConfiguration {

    BeanMapperProperties beanMapperProperties;

    public BeanMapperAutoConfiguration(BeanMapperProperties beanMapperProperties) {
        this.beanMapperProperties = beanMapperProperties;
    }

    @Bean
    public BeanMapper beanMapper() {
        BeanMapper beanMapper = new BeanMapper();
        try (ScanResult scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .enableClassInfo()
                .whitelistPackages(beanMapperProperties.getScanPackage())
                .scan()) {
            scanResult.getClassesWithAnnotation(BeanMapping.class.getName()).forEach(classInfo -> {
                Class mappingClass = classInfo.loadClass();
                if (!OneWayMapping.class.isAssignableFrom(mappingClass)) {
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
