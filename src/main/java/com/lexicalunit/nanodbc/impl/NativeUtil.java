package com.lexicalunit.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_java.h")
@Namespace("nanodbc")
class NativeUtil {
    static {
        Loader.load();
    }

    static native void execute(@ByRef NativeResult result, @ByRef NativeConnection conn, String query,
            long batchOperations, long timeout);

}