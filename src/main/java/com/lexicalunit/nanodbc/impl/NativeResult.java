package com.lexicalunit.nanodbc.impl;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

import com.lexicalunit.nanodbc.Result;

@Platform(include = "nanodbc_java.h")
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
    @Name("column_name")
    public native @StdString String getColumnName(short column);

    @Override
    public JDBCType getColumnType(short column) {
        return JDBCType.valueOf(getColumnDatatype(column));
    }

    @Override
    public JDBCType getColumnType(String columnName) {
        return JDBCType.valueOf(getColumnDatatype(columnName));
    }

    @Name("column_datatype")
    private native int getColumnDatatype(short column);

    @Name("column_datatype")
    private native int getColumnDatatype(String columnName);

    @Override
    @Name("is_null")
    public native boolean isNull(short column);

    @Override
    @Name("is_null")
    public native boolean isNull(String columnName);

    @Override
    @Name("get<int32_t>")
    public native int getInt(short column);

    @Override
    @Name("get<int32_t>")
    public native int getInt(String columnName);

    @Override
    @Name("get<float>")
    public native float getFloat(short column);

    @Override
    @Name("get<float>")
    public native float getFloat(String columnName);

    @Override
    @Name("get<double>")
    public native double getDouble(short column);

    @Override
    @Name("get<double>")
    public native double getDouble(String columnName);

    @Override
    @Name("get<::nanodbc::string_type>")
    public native @StdString String getString(short column);

    @Override
    @Name("get<::nanodbc::string_type>")
    public native @StdString String getString(String columnName);

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
    private native void getDateRef(String columnName, @ByRef NativeDate date);

    @Override
    public LocalDateTime getDateTime(short column) {
        try (NativeDateTime dateTime = new NativeDateTime()) {
            getDateTimeRef(column, dateTime);
            return dateTime.toLocalDateTime();
        }
    }

    @Override
    public LocalDateTime getDateTime(String columnName) {
        try (NativeDateTime dateTime = new NativeDateTime()) {
            getDateTimeRef(columnName, dateTime);
            return dateTime.toLocalDateTime();
        }
    }

    @Name("get_ref<::nanodbc::timestamp>")
    private native void getDateTimeRef(short column, @ByRef NativeDateTime date);

    @Name("get_ref<::nanodbc::timestamp>")
    private native void getDateTimeRef(String columnName, @ByRef NativeDateTime date);

}
