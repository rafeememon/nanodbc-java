package io.nanodbc.impl;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
public class NativeExt {
    static {
        Loader.load();
    }

    public static native void execute(@ByRef NativeResult result, @ByRef NativeConnection conn, String query);

    public static native void execute(@ByRef NativeResult result, @ByRef NativeConnection conn, String query,
            long timeout);

    public static native void execute(@ByRef NativeResult result, @ByRef NativeStatement statement);

    @Name("just_execute2")
    public static native void justExecute(@ByRef NativeConnection conn, String query);

    @Name("just_execute2")
    public static native void justExecute(@ByRef NativeConnection conn, String query, long timeout);

    @Name("just_execute")
    public static native void justExecute(@ByRef NativeStatement statement);

    private NativeExt() {
        // utility class
    }

}
