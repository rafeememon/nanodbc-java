package io.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
class NativeExt {
    static {
        Loader.load();
    }

    static native void execute(@ByRef NativeResult result, @ByRef NativeConnection conn, String query);

    static native void execute(@ByRef NativeResult result, @ByRef NativeConnection conn, String query, long timeout);

    static native void execute(@ByRef NativeResult result, @ByRef NativeStatement statement);

    @Name("just_execute2")
    static native void justExecute(@ByRef NativeConnection conn, String query);

    @Name("just_execute2")
    static native void justExecute(@ByRef NativeConnection conn, String query, long timeout);

    @Name("just_execute")
    static native void justExecute(@ByRef NativeStatement statement);

}
