package io.nanodbc.impl;

import java.time.LocalDate;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.MemberSetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
@Name("date")
public class NativeDate extends Pointer {
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

    public LocalDate toLocalDate() {
        return LocalDate.of(getYear(), getMonth(), getDay());
    }

    public static NativeDate fromLocalDate(LocalDate localDate) {
        NativeDate date = null;
        try {
            date = new NativeDate();
            date.setYear(localDate.getYear());
            date.setMonth(localDate.getMonthValue());
            date.setDay(localDate.getDayOfMonth());
            return date;
        } catch (Exception e) {
            Pointers.closeQuietly(date);
            throw e;
        }
    }

}
