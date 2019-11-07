package com.yangxiaochen.mapper.test.cases;

public class Foo {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "code='" + code + '\'' +
                '}';
    }
}
