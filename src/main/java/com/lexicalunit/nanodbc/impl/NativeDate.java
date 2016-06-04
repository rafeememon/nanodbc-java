package com.lexicalunit.nanodbc.impl;

import java.time.LocalDate;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
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

    LocalDate toLocalDate() {
        return LocalDate.of(getYear(), getMonth(), getDay());
    }

    static NativeDate fromLocalDate(LocalDate localDate) {
        NativeDate date = new NativeDate();
        date.setYear(localDate.getYear());
        date.setMonth(localDate.getMonthValue());
        date.setDay(localDate.getDayOfMonth());
        return date;
    }

}
