package com.slimgears.sample;

import com.slimgears.util.autovalue.annotations.AutoValuePrototype;

import javax.annotation.Nullable;

@AutoValuePrototype()
public interface SampleValuePrototype {
    int intValue();

    @SampleFieldAnnotation(strValue = "test")
    double doubleValue();

    @SampleFieldAnnotation
    @Nullable
    String strValue();

    default String calculatedString() {
        return strValue() + doubleValue() + intValue();
    }
}
