package com.yangxiaochen.mapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BeanMapper {

    List<OneWayMapping> oneWayMappings = new ArrayList<>();

    public void addMapping(OneWayMapping mapping) {
        oneWayMappings.add(mapping);
        if (mapping instanceof TwoWayMapping) {
            oneWayMappings.add(new ReverseMappingWrapper((TwoWayMapping) mapping));
        }
    }


    /**
     * 本方法不支持 source 是一个泛型类型
     *
     * @param source
     * @param targetType
     * @param <T>
     * @param <S>
     * @return
     * @see BeanMapper#map(Object, TypeRef, TypeRef)
     */
    public <T, S> T map(S source, Class<T> targetType) {
        Type sourceType = source.getClass();
        return doMap(source, sourceType, targetType);
    }

    /**
     * 本方法不支持 source 是一个泛型类型
     *
     * @param source
     * @param targetType
     * @param <T>
     * @param <S>
     * @return
     * @see BeanMapper#map(Object, TypeRef, TypeRef)
     */
    public <T, S> T map(S source, TypeRef<T> targetType) {
        Type sourceType = source.getClass();
        return doMap(source, sourceType, targetType.getType());
    }

    /**
     * 如果 source 是一个泛型类型, 必须使用 TypeRef 来指定泛型 type
     *
     * @param source
     * @param sourceType
     * @param targetType
     * @param <T>
     * @param <S>
     * @return
     */
    public <T, S> T map(S source, TypeRef<S> sourceType, TypeRef<T> targetType) {
        return doMap(source, sourceType.getType(), targetType.getType());
    }


    private <T, S> T doMap(S source, Type sourceType, Type targetType) {
        OneWayMapping<S, T> targetMapping = oneWayMappings.stream()
                .filter(mapping -> mapping.getSourceType().equals(sourceType) && mapping.getTargetType().equals(targetType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no mapping for type: " + sourceType.getTypeName() + " to type: " + targetType.getTypeName()));
        return targetMapping.map(source);
    }
}
