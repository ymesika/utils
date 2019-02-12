package com.slimgears.util.autovalue.expressions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.slimgears.util.autovalue.annotations.PropertyMeta;
import com.slimgears.util.autovalue.expressions.internal.BooleanPropertyExpression;
import com.slimgears.util.autovalue.expressions.internal.ComparablePropertyExpression;
import com.slimgears.util.autovalue.expressions.internal.NumericPropertyExpression;
import com.slimgears.util.autovalue.expressions.internal.ObjectPropertyExpression;
import com.slimgears.util.autovalue.expressions.internal.StringPropertyExpression;

public interface PropertyExpression<S, T, B, V> extends ObjectExpression<S, V> {
    @JsonProperty ObjectExpression<S, T> target();
    @JsonProperty PropertyMeta<T, B, V> property();

    static <S, T, B, V> ObjectPropertyExpression<S, T, B, V> ofObject(ObjectExpression<S, T> target, PropertyMeta<T, B, V> property) {
        return ObjectPropertyExpression.create(Type.Property, target, property);
    }

    static <S, T, B, V extends Comparable<V>> ComparablePropertyExpression<S, T, B, V> ofComparable(ObjectExpression<S, T> target, PropertyMeta<T, B, V> property) {
        return ComparablePropertyExpression.create(Type.ComparableProperty, target, property);
    }

    static <S, T, B> StringPropertyExpression<S, T, B> ofString(ObjectExpression<S, T> target, PropertyMeta<T, B, String> property) {
        return StringPropertyExpression.create(Type.StringProperty, target, property);
    }

    static <S, T, B> BooleanPropertyExpression<S, T, B> ofBoolean(ObjectExpression<S, T> target, PropertyMeta<T, B, Boolean> property) {
        return BooleanPropertyExpression.create(Type.BooleanProperty, target, property);
    }

    static <S, T, B, V extends Number & Comparable<V>> NumericPropertyExpression<S, T, B, V> ofNumeric(ObjectExpression<S, T> target, PropertyMeta<T, B, V> property) {
        return NumericPropertyExpression.create(Type.NumericProperty, target, property);
    }
}
