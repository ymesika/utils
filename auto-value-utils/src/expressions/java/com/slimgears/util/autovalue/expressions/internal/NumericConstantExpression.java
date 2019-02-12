package com.slimgears.util.autovalue.expressions.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import com.slimgears.util.autovalue.expressions.ConstantExpression;
import com.slimgears.util.autovalue.expressions.NumericExpression;

@AutoValue
public abstract class NumericConstantExpression<S, V extends Number & Comparable<V>> implements ConstantExpression<S, V>, NumericExpression<S, V> {
    @JsonCreator
    public static <S, V extends Number & Comparable<V>> NumericConstantExpression<S, V> create(
            @JsonProperty("type") Type type,
            @JsonProperty("value") V value) {
        return new AutoValue_NumericConstantExpression<>(type, value);
    }
}
