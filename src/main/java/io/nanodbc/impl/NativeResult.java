package io.nanodbc.impl;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdWString;

import io.nanodbc.Result;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
@Name("result")
public class NativeResult extends Pointer implements Result {
    static {
        Loader.load();
    }

    public NativeResult() {
        allocate();
    }

    private native void allocate();

    @Override
    public native boolean next();

    @Override
    @Name("columns")
    public native short getNumColumns();

    @Override
    @Name("affected_rows")
    public native long getAffectedRows();

    @Override
    @Name("column_name")
    public native String getColumnName(short column);

    @Override
    public JDBCType getColumnType(short column) {
        return JDBCType.valueOf(getColumnDatatype(column));
    }

    @Override
    public JDBCType getColumnType(@StdWString String columnName) {
        return JDBCType.valueOf(getColumnDatatype(columnName));
    }

    @Name("column_datatype")
    private native int getColumnDatatype(short column);

    @Name("column_datatype")
    private native int getColumnDatatype(@StdWString String columnName);

    @Override
    @Name("is_null")
    public native boolean isNull(short column);

    @Override
    @Name("is_null")
    public native boolean isNull(@StdWString String columnName);

    @Override
    @Name("get<int32_t>")
    public native int getInt(short column);

    @Override
    @Name("get<int32_t>")
    public native int getInt(@StdWString String columnName);

    @Override
    @Name("get<long long>")
    public native long getLong(short column);

    @Override
    @Name("get<long long>")
    public native long getLong(@StdWString String columnName);

    @Override
    @Name("get<float>")
    public native float getFloat(short column);

    @Override
    @Name("get<float>")
    public native float getFloat(@StdWString String columnName);

    @Override
    @Name("get<double>")
    public native double getDouble(short column);

    @Override
    @Name("get<double>")
    public native double getDouble(@StdWString String columnName);

    @Override
    public String getString(short column) {
        return "Test";
    }

    @Override
    public String getString(String columnName) {
        return "Test";
    }

    /*-
    @Override
    @Name("get<::nanodbc::string>")
    public native @StdWString String getString(short column);
    
    @Override
    @Name("get<::nanodbc::string>")
    public native @StdWString String getString(@StdWString String columnName);
    */

    @Override
    public LocalDate getDate(short column) {
        try (NativeDate date = new NativeDate()) {
            getDateRef(column, date);
            return date.toLocalDate();
        }
    }

    @Override
    public LocalDate getDate(String columnName) {
        try (NativeDate date = new NativeDate()) {
            getDateRef(columnName, date);
            return date.toLocalDate();
        }
    }

    @Name("get_ref<::nanodbc::date>")
    private native void getDateRef(short column, @ByRef NativeDate date);

    @Name("get_ref<::nanodbc::date>")
    private native void getDateRef(@StdWString String columnName, @ByRef NativeDate date);

    @Override
    public LocalTime getTime(short column) {
        try (NativeTime time = new NativeTime()) {
            getTimeRef(column, time);
            return time.toLocalTime();
        }
    }

    @Override
    public LocalTime getTime(@StdWString String columnName) {
        try (NativeTime time = new NativeTime()) {
            getTimeRef(columnName, time);
            return time.toLocalTime();
        }
    }

    @Name("get_ref<::nanodbc::time>")
    private native void getTimeRef(short column, @ByRef NativeTime time);

    @Name("get_ref<::nanodbc::time>")
    private native void getTimeRef(@StdWString String columnName, @ByRef NativeTime time);

    @Override
    public LocalDateTime getDateTime(short column) {
        try (NativeDateTime dateTime = new NativeDateTime()) {
            getDateTimeRef(column, dateTime);
            return dateTime.toLocalDateTime();
        }
    }

    @Override
    public LocalDateTime getDateTime(@StdWString String columnName) {
        try (NativeDateTime dateTime = new NativeDateTime()) {
            getDateTimeRef(columnName, dateTime);
            return dateTime.toLocalDateTime();
        }
    }

    @Name("get_ref<::nanodbc::timestamp>")
    private native void getDateTimeRef(short column, @ByRef NativeDateTime date);

    @Name("get_ref<::nanodbc::timestamp>")
    private native void getDateTimeRef(@StdWString String columnName, @ByRef NativeDateTime date);

}
