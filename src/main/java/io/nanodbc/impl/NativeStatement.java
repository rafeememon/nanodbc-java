package io.nanodbc.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

import io.nanodbc.Statement;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
@Name("statement")
public class NativeStatement extends Pointer implements Statement {
    static {
        Loader.load();
    }

    private final Map<Short, Pointer> parameterPointers = new HashMap<>();

    public NativeStatement(NativeConnection connection, String query) {
        allocate(connection, query);
    }

    public NativeStatement(NativeConnection connection, String query, long timeout) {
        allocate(connection, query, timeout);
    }

    private native void allocate(@ByRef NativeConnection connection, String query);

    private native void allocate(@ByRef NativeConnection connection, String query, long timeout);

    @Override
    public void bind(short column, int value) {
        IntPointer intPointer = new IntPointer(1L);
        intPointer.put(value);
        Pointer oldPointer = parameterPointers.put(column, intPointer);
        bind(column, intPointer);
        close(oldPointer);
    }

    @Name("bind<int32_t>")
    private native void bind(short column, IntPointer intPointer);

    @Override
    public void bind(short column, long value) {
        LongPointer longPointer = new LongPointer(1L);
        longPointer.put(value);
        Pointer oldPointer = parameterPointers.put(column, longPointer);
        bind(column, longPointer);
        close(oldPointer);
    }

    @Name("bind<long long>")
    private native void bind(short column, LongPointer longPointer);

    @Override
    public void bind(short column, float value) {
        FloatPointer floatPointer = new FloatPointer(1L);
        floatPointer.put(value);
        Pointer oldPointer = parameterPointers.put(column, floatPointer);
        bind(column, floatPointer);
        close(oldPointer);
    }

    @Name("bind<float>")
    private native void bind(short column, FloatPointer floatPointer);

    @Override
    public void bind(short column, double value) {
        DoublePointer doublePointer = new DoublePointer(1L);
        doublePointer.put(value);
        Pointer oldPointer = parameterPointers.put(column, doublePointer);
        bind(column, doublePointer);
        close(oldPointer);
    }

    @Name("bind<double>")
    private native void bind(short column, DoublePointer doublePointer);

    @Override
    public void bind(short column, String value) {
        BytePointer bytePointer = new BytePointer(value);
        Pointer oldPointer = parameterPointers.put(column, bytePointer);
        bind(column, bytePointer);
        close(oldPointer);
    }

    @Name("bind<::nanodbc::string::value_type>")
    private native void bind(short column, @Cast("::nanodbc::string::value_type*") BytePointer bytePointer);

    @Override
    public void bind(short column, LocalDate date) {
        NativeDate datePointer = NativeDate.fromLocalDate(date);
        Pointer oldPointer = parameterPointers.put(column, datePointer);
        bind(column, datePointer);
        close(oldPointer);
    }

    @Name("bind<::nanodbc::date>")
    private native void bind(short column, NativeDate datePointer);

    @Override
    public void bind(short column, LocalTime time) {
        NativeTime timePointer = NativeTime.fromLocalTime(time);
        Pointer oldPointer = parameterPointers.put(column, timePointer);
        bind(column, timePointer);
        close(oldPointer);
    }

    @Name("bind<::nanodbc::time>")
    private native void bind(short column, NativeTime timePointer);

    @Override
    public void bind(short column, LocalDateTime dateTime) {
        NativeDateTime dateTimePointer = NativeDateTime.fromLocalDateTime(dateTime);
        Pointer oldPointer = parameterPointers.put(column, dateTimePointer);
        bind(column, dateTimePointer);
        close(oldPointer);
    }

    @Name("bind<::nanodbc::timestamp>")
    private native void bind(short column, NativeDateTime dateTimePointer);

    @Override
    public void bindNull(short column) {
        Pointer oldPointer = parameterPointers.remove(column);
        bindNullNative(column);
        close(oldPointer);
    }

    @Name("bind_null")
    private native void bindNullNative(short column);

    @Override
    public NativeResult execute() {
        NativeResult result = new NativeResult();
        NativeExt.execute(result, this);
        return result;
    }

    @Override
    public void justExecute() {
        NativeExt.justExecute(this);
    }

    @Override
    public void close() {
        for (Pointer parameterPointer : parameterPointers.values()) {
            parameterPointer.close();
        }
        parameterPointers.clear();
        super.close();
    }

    private static void close(Pointer pointer) {
        if (pointer != null) {
            pointer.close();
        }
    }

}
