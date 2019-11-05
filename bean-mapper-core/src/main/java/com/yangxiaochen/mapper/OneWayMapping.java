package com.yangxiaochen.mapper;

import java.lang.reflect.Type;

/**
 * define a mapping from source to target
 * @param <S>
 * @param <T>
 */
public abstract class OneWayMapping<S, T> {

    private Type sourceType;
    private Type targetType;

//    private Class<S> sourceType;
//    private Class<T> targetType;

    public OneWayMapping(Class<S> sourceType, Class<T> targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public OneWayMapping(TypeRef<S> sourceType, TypeRef<T> targetType) {
        this.sourceType = sourceType.getType();
        this.targetType = targetType.getType();
    }

    public OneWayMapping(Type sourceType, Type targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    /**
     * source object type
     * @return
     */
    Type getSourceType() {
        return sourceType;
    }

    /**
     * target object type
     * @return
     */
    Type getTargetType() {
        return targetType;
    }

    /**
     * map source object to target object
     * @param s
     * @return
     */
    public abstract T map(S s);
}
