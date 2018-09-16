package io.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

import io.nanodbc.Connection;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
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

    public NativeConnection(String dsn, String user, String pass, long timeout) {
        allocate(dsn, user, pass, timeout);
    }

    private native void allocate();

    private native void allocate(String connectionString, long timeout);

    private native void allocate(String dsn, String user, String pass, long timeout);

    @Override
    public native void connect(String connectionString, long timeout);

    @Override
    public native void connect(String dsn, String user, String pass, long timeout);

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

    @Override
    public NativeStatement prepare(String query, long timeout) {
        return NativeStatement.create(this, query, timeout);
    }

}
