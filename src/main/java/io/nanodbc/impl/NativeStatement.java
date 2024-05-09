package io.nanodbc.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.AsUtf16;
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

    private native void allocate(@ByRef NativeConnection connection, @AsUtf16 @NanodbcString String query);

    private native void allocate(@ByRef NativeConnection connection, @AsUtf16 @NanodbcString String query,
            long timeout);

    @Override
    public void bind(short column, int value) {
        IntPointer intPointer = new IntPointer(1L);
        intPointer.put(value);
        setParameterPointer(column, intPointer, this::bind);
    }

    @Name("bind<int32_t>")
    private native void bind(short column, IntPointer intPointer);

    @Override
    public void bind(short column, long value) {
        LongPointer longPointer = new LongPointer(1L);
        longPointer.put(value);
        setParameterPointer(column, longPointer, this::bind);
    }

    @Name("bind<long long>")
    private native void bind(short column, LongPointer longPointer);

    @Override
    public void bind(short column, float value) {
        FloatPointer floatPointer = new FloatPointer(1L);
        floatPointer.put(value);
        setParameterPointer(column, floatPointer, this::bind);
    }

    @Name("bind<float>")
    private native void bind(short column, FloatPointer floatPointer);

    @Override
    public void bind(short column, double value) {
        DoublePointer doublePointer = new DoublePointer(1L);
        doublePointer.put(value);
        setParameterPointer(column, doublePointer, this::bind);
    }

    @Name("bind<double>")
    private native void bind(short column, DoublePointer doublePointer);

    @Override
    public void bind(short column, String value) {
        setParameterPointer(column, new BytePointer(value), this::bind);
    }

    @Name("bind<::nanodbc::string::value_type>")
    private native void bind(short column, @Cast("::nanodbc::string::value_type*") BytePointer bytePointer);

    @Override
    public void bind(short column, LocalDate value) {
        setParameterPointer(column, NativeDate.fromLocalDate(value), this::bind);
    }

    @Name("bind<::nanodbc::date>")
    private native void bind(short column, NativeDate datePointer);

    @Override
    public void bind(short column, LocalTime value) {
        setParameterPointer(column, NativeTime.fromLocalTime(value), this::bind);
    }

    @Name("bind<::nanodbc::time>")
    private native void bind(short column, NativeTime timePointer);

    @Override
    public void bind(short column, LocalDateTime value) {
        setParameterPointer(column, NativeDateTime.fromLocalDateTime(value), this::bind);
    }

    @Name("bind<::nanodbc::timestamp>")
    private native void bind(short column, NativeDateTime dateTimePointer);

    @Override
    public void bindNull(short column) {
        try {
            bindNullNative(column);
        } finally {
            Pointers.closeQuietly(parameterPointers.remove(column));
        }
    }

    @Name("bind_null")
    private native void bindNullNative(short column);

    @Override
    public NativeResult execute() {
        NativeResult result = null;
        try {
            result = new NativeResult();
            NativeExt.execute(result, this);
            return result;
        } catch (Exception e) {
            Pointers.closeQuietly(result);
            throw e;
        }
    }

    @Override
    public void justExecute() {
        NativeExt.justExecute(this);
    }

    @Override
    public void close() {
        for (Pointer parameterPointer : parameterPointers.values()) {
            Pointers.closeQuietly(parameterPointer);
        }
        parameterPointers.clear();
        super.close();
    }

    private <T extends Pointer> void setParameterPointer(short column, T pointer, BiConsumer<Short, T> binder) {
        try {
            binder.accept(column, pointer);
        } finally {
            Pointers.closeQuietly(parameterPointers.put(column, pointer));
        }
    }

}
