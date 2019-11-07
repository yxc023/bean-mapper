package com.yangxiaochen.mapper.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yangxiaochen
 */
@ConfigurationProperties(prefix = "yxc.bean.mapper")
public class BeanMapperProperties {
    private String scanPackage;


    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }
}
