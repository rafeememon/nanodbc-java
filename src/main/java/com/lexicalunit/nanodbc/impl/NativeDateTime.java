package com.lexicalunit.nanodbc.impl;

import java.time.LocalDateTime;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
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

    NativeDateTime() {
        allocate();
    }

    private native void allocate();

    @MemberGetter
    @Name("year")
    native int getYear();

    @MemberSetter
    @Name("year")
    native void setYear(int year);

    @MemberGetter
    @Name("month")
    native int getMonth();

    @MemberSetter
    @Name("month")
    native void setMonth(int month);

    @MemberGetter
    @Name("day")
    native int getDay();

    @MemberSetter
    @Name("day")
    native void setDay(int day);

    @MemberGetter
    @Name("hour")
    native int getHour();

    @MemberSetter
    @Name("hour")
    native void setHour(int hour);

    @MemberGetter
    @Name("min")
    native int getMinute();

    @MemberSetter
    @Name("min")
    native void setMinute(int minute);

    @MemberGetter
    @Name("sec")
    native int getSecond();

    @MemberSetter
    @Name("sec")
    native void setSecond(int second);

    @MemberGetter
    @Name("fract")
    native int getFract();

    @MemberSetter
    @Name("fract")
    native void setFract(int fract);

    LocalDateTime toLocalDateTime() {
        return LocalDateTime.of(getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond(),
                getFract() * 100_000);
    }

    static NativeDateTime fromLocalDateTime(LocalDateTime localDateTime) {
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
