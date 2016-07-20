package com.lexicalunit.nanodbc.impl;

import java.time.LocalTime;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_java.h")
@Namespace("nanodbc")
@Name("time")
class NativeTime extends Pointer {
    static {
        Loader.load();
    }

    public NativeTime() {
        allocate();
    }

    private native void allocate();

    @MemberGetter
    @Name("hour")
    public native int getHour();

    @MemberSetter
    @Name("hour")
    public native void setHour(int hour);

    @MemberGetter
    @Name("min")
    public native int getMinute();

    @MemberSetter
    @Name("min")
    public native void setMinute(int minute);

    @MemberGetter
    @Name("sec")
    public native int getSecond();

    @MemberSetter
    @Name("sec")
    public native void setSecond(int second);

    public LocalTime toLocalTime() {
        return LocalTime.of(getHour(), getMinute(), getSecond());
    }

    public static NativeTime fromLocalTime(LocalTime localTime) {
        NativeTime time = new NativeTime();
        time.setHour(localTime.getHour());
        time.setMinute(localTime.getMinute());
        time.setSecond(localTime.getSecond());
        return time;
    }

}
