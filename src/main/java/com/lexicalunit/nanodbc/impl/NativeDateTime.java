package com.lexicalunit.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_java.h")
@Namespace("nanodbc")
@Name("timestamp")
class NativeDateTime extends Pointer {
    static {
        Loader.load();
    }

    public NativeDateTime() {
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

    @MemberGetter
    @Name("hour")
    public native int getHour();

    @MemberGetter
    @Name("min")
    public native int getMinute();

    @MemberGetter
    @Name("sec")
    public native int getSecond();

    @MemberGetter
    @Name("fract")
    public native int getFract();

}
