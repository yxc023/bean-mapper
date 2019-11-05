package com.yangxiaochen.mapper;

import java.lang.reflect.Type;

class ReverseMappingWrapper<T, S> extends OneWayMapping<T, S> {

    private TwoWayMapping<S, T> twoWayMapping;


    public ReverseMappingWrapper(TwoWayMapping<S, T> twoWayMapping) {
        super(twoWayMapping.getTargetType(), twoWayMapping.getSourceType());
        this.twoWayMapping = twoWayMapping;
    }

    @Override
    public Type getSourceType() {
        return twoWayMapping.getTargetType();
    }

    @Override
    public Type getTargetType() {
        return twoWayMapping.getSourceType();
    }

    @Override
    public S map(T t) {
        return twoWayMapping.mapReverse(t);
    }
}
