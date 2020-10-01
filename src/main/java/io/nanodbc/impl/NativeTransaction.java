package io.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

import io.nanodbc.Transaction;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
@Name("transaction")
public class NativeTransaction extends Pointer implements Transaction {
    static {
        Loader.load();
    }

    public NativeTransaction(NativeConnection connection) {
        allocate(connection);
    }

    private native void allocate(@ByRef NativeConnection connection);

    @Override
    public native void commit();

    @Override
    public native void rollback();

}
