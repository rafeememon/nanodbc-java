package com.lexicalunit.nanodbc.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

import com.lexicalunit.nanodbc.Statement;

@Platform(include = "nanodbc_java.h")
@Namespace("nanodbc")
@Name("statement")
class NativeStatement extends Pointer implements Statement {
    static {
        Loader.load();
    }

    private final List<Pointer> parameterPointers;

    public static NativeStatement create(NativeConnection connection, String query, long timeout) {
        return new NativeStatement(connection, query, timeout, new ArrayList<>());
    }

    NativeStatement(NativeConnection connection, String query, long timeout, List<Pointer> parameterPointers) {
        allocate(connection, query, timeout);
        this.parameterPointers = parameterPointers;
    }

    private native void allocate(@ByRef NativeConnection connection, String query, long timeout);

    @Override
    public void bind(short column, int value) {
        IntPointer intPointer = new IntPointer(1L);
        intPointer.put(value);
        parameterPointers.add(intPointer);
        bind(column, intPointer);
    }

    @Name("bind<int32_t>")
    private native void bind(short column, IntPointer intPointer);

    @Override
    public void bind(short column, float value) {
        FloatPointer floatPointer = new FloatPointer(1L);
        floatPointer.put(value);
        parameterPointers.add(floatPointer);
        bind(column, floatPointer);
    }

    @Name("bind<float>")
    private native void bind(short column, FloatPointer floatPointer);

    @Override
    public void bind(short column, double value) {
        DoublePointer doublePointer = new DoublePointer(1L);
        doublePointer.put(value);
        parameterPointers.add(doublePointer);
        bind(column, doublePointer);
    }

    @Name("bind<double>")
    private native void bind(short column, DoublePointer doublePointer);

    @Override
    public void bind(short column, String value) {
        BytePointer bytePointer = new BytePointer(value);
        parameterPointers.add(bytePointer);
        bind(column, bytePointer);
    }

    @Name("bind<::nanodbc::string::value_type>")
    private native void bind(short column, @Cast("::nanodbc::string::value_type*") BytePointer bytePointer);

    @Override
    public void bind(short column, LocalDate date) {
        NativeDate datePointer = NativeDate.fromLocalDate(date);
        parameterPointers.add(datePointer);
        bind(column, datePointer);
    }

    @Name("bind<::nanodbc::date>")
    private native void bind(short column, NativeDate datePointer);

    @Override
    public void bind(short column, LocalTime time) {
        NativeTime timePointer = NativeTime.fromLocalTime(time);
        parameterPointers.add(timePointer);
        bind(column, timePointer);
    }

    @Name("bind<::nanodbc::time>")
    private native void bind(short column, NativeTime timePointer);

    @Override
    public void bind(short column, LocalDateTime dateTime) {
        NativeDateTime dateTimePointer = NativeDateTime.fromLocalDateTime(dateTime);
        parameterPointers.add(dateTimePointer);
        bind(column, dateTimePointer);
    }

    @Name("bind<::nanodbc::timestamp>")
    private native void bind(short column, NativeDateTime dateTimePointer);

    @Override
    @Name("bind_null")
    public native void bindNull(short column);

    @Override
    public NativeResult execute(long batchOperations) {
        NativeResult result = new NativeResult();
        NativeUtil.execute(result, this, batchOperations);
        return result;
    }

    @Override
    public void close() {
        for (Pointer parameterPointer : parameterPointers) {
            parameterPointer.close();
        }
        super.close();
    }

}
