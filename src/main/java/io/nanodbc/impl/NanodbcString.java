package io.nanodbc.impl;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.bytedeco.javacpp.annotation.Cast;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Cast({"nanodbc::string", "&"})
// @Adapter("StringAdapter")
public @interface NanodbcString {
    // empty
}
