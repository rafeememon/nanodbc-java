package io.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

import io.nanodbc.Connection;
import io.nanodbc.Transaction;

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

    public NativeConnection(String connectionString) {
        allocate(connectionString);
    }

    public NativeConnection(String connectionString, long timeout) {
        allocate(connectionString, timeout);
    }

    public NativeConnection(String dsn, String user, String pass, long timeout) {
        allocate(dsn, user, pass, timeout);
    }

    private native void allocate();

    private native void allocate(String connectionString);

    private native void allocate(String connectionString, long timeout);

    private native void allocate(String dsn, String user, String pass, long timeout);

    @Override
    public native void connect(String connectionString);

    @Override
    public native void connect(String connectionString, long timeout);

    @Override
    public native void connect(String dsn, String user, String pass, long timeout);

    @Override
    public native boolean connected();

    @Override
    public native void disconnect();

    @Override
    public NativeResult execute(String query) {
        NativeResult result = null;
        try {
            result = new NativeResult();
            NativeExt.execute(result, this, query);
            return result;
        } catch (Exception e) {
            Pointers.closeQuietly(result);
            throw e;
        }
    }

    @Override
    public NativeResult execute(String query, long timeout) {
        NativeResult result = null;
        try {
            result = new NativeResult();
            NativeExt.execute(result, this, query, timeout);
            return result;
        } catch (Exception e) {
            Pointers.closeQuietly(result);
            throw e;
        }
    }

    @Override
    public void justExecute(String query) {
        NativeExt.justExecute(this, query);
    }

    @Override
    public void justExecute(String query, long timeout) {
        NativeExt.justExecute(this, query, timeout);
    }

    @Override
    public NativeStatement prepare(String query) {
        return new NativeStatement(this, query);
    }

    @Override
    public NativeStatement prepare(String query, long timeout) {
        return new NativeStatement(this, query, timeout);
    }

    @Override
    public Transaction beginTransaction() {
        return new NativeTransaction(this);
    }

}
