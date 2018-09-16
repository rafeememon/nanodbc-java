package com.lexicalunit.nanodbc.impl;

import java.time.LocalDateTime;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_ext.h")
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

    @MemberSetter
    @Name("year")
    public native void setYear(int year);

    @MemberGetter
    @Name("month")
    public native int getMonth();

    @MemberSetter
    @Name("month")
    public native void setMonth(int month);

    @MemberGetter
    @Name("day")
    public native int getDay();

    @MemberSetter
    @Name("day")
    public native void setDay(int day);

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

    @MemberGetter
    @Name("fract")
    public native int getFract();

    @MemberSetter
    @Name("fract")
    public native void setFract(int fract);

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond());
    }

    public static NativeDateTime fromLocalDateTime(LocalDateTime localDateTime) {
        NativeDateTime dateTime = new NativeDateTime();
        dateTime.setYear(localDateTime.getYear());
        dateTime.setMonth(localDateTime.getMonthValue());
        dateTime.setDay(localDateTime.getDayOfMonth());
        dateTime.setHour(localDateTime.getHour());
        dateTime.setMinute(localDateTime.getMinute());
        dateTime.setSecond(localDateTime.getSecond());
        dateTime.setFract(localDateTime.getNano() / 100_000);
        return dateTime;
    }

}
