package com.yangxiaochen.mapper;

public abstract class TwoWayMapping<S, T> extends OneWayMapping<S,T> {

    public TwoWayMapping(Class<S> sourceType, Class<T> targetType) {
        super(sourceType, targetType);
    }

    public TwoWayMapping(TypeRef<S> sourceType, TypeRef<T> targetType) {
        super(sourceType, targetType);
    }

    public abstract S mapReverse(T t);
}
