package com.yangxiaochen.mapper.test.cases;

import com.yangxiaochen.mapper.BeanMapper;
import com.yangxiaochen.mapper.test.AbstractTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

public class TestCase extends AbstractTestCase {

    @Autowired
    BeanMapper beanMapper;
    @Test
    public void test() {
        Foo foo = new Foo();
        foo.setCode("123");
        Bar bar = beanMapper.map(foo, Bar.class);
        System.out.println(bar);
    }
}
