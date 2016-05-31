package com.lexicalunit.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_java.h")
@Namespace("nanodbc")
@Name("date")
class NativeDate extends Pointer {
    static {
        Loader.load();
    }

    public NativeDate() {
        allocate();
    }

    private native void allocate();

    @MemberGetter
    @Name("year")
    public native int getYear();

    @MemberGetter
    @Name("month")
    public native int getMonth();

    @MemberGetter
    @Name("day")
    public native int getDay();

}
