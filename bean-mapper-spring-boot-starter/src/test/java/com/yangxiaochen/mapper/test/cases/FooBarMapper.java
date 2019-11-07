package com.yangxiaochen.mapper.test.cases;


import com.yangxiaochen.mapper.TwoWayMapping;
import com.yangxiaochen.mapper.spring.BeanMapping;

@BeanMapping
public class FooBarMapper extends TwoWayMapping<Foo, Bar> {
    public FooBarMapper() {
        super(Foo.class, Bar.class);
    }

    @Override
    public Foo mapReverse(Bar bar) {
        Foo foo = new Foo();
        foo.setCode(bar.getName());
        return foo;
    }

    @Override
    public Bar map(Foo foo) {
        Bar bar = new Bar();
        bar.setName(foo.getCode());
        return bar;
    }
}
