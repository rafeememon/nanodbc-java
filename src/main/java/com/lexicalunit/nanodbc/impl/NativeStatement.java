package com.lexicalunit.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
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

    NativeStatement(NativeConnection connection, String query, long timeout) {
        allocate(connection, query, timeout);
    }

    private native void allocate(@ByRef NativeConnection connection, String query, long timeout);

    @Override
    public NativeResult execute(long batchOperations) {
        NativeResult result = new NativeResult();
        NativeUtil.execute(result, this, batchOperations);
        return result;
    }

}
