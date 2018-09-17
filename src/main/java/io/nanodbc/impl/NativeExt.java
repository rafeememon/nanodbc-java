package io.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
class NativeExt {
    static {
        Loader.load();
    }

    static native void execute(@ByRef NativeResult result, @ByRef NativeConnection conn, String query, long timeout);

    static native void execute(@ByRef NativeResult result, @ByRef NativeStatement statement);

}
