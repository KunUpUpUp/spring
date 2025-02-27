package com.seasugar.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// FIELD用在属性上
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DI {
}
