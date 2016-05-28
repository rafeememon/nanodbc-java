package com.lexicalunit.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

import com.lexicalunit.nanodbc.Connection;

@Platform(include = "nanodbc_java.h")
@Namespace("nanodbc")
@Name("connection")
public class NativeConnection extends Pointer implements Connection {
    static {
        Loader.load();
    }

    public NativeConnection() {
        allocate();
    }

    public NativeConnection(String connectionString, long timeout) {
        allocate(connectionString, timeout);
    }

    private native void allocate();

    private native void allocate(String connectionString, long timeout);

    @Override
    public native void connect(String connectionString, long timeout);

    @Override
    public native boolean connected();

    @Override
    public native void disconnect();

    @Override
    public NativeResult execute(String query, long batchOperations, long timeout) {
        NativeResult result = new NativeResult();
        NativeUtil.execute(result, this, query, batchOperations, timeout);
        return result;
    }

}
