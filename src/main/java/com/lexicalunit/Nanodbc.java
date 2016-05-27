package com.lexicalunit;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = { "nanodbc.h", "nanodbc_java.h" })
@Namespace("nanodbc")
public class Nanodbc {

    @Name("connection")
    public static class Connection extends Pointer {
        static {
            Loader.load();
        }

        public Connection() {
            allocate();
        }

        public Connection(String connectionString, long timeout) {
            allocate(connectionString, timeout);
        }

        private native void allocate();

        private native void allocate(String connectionString, long timeout);

        public native void connect(String connectionString, long timeout);

        public native boolean connected();

        public native void disconnect();

        public Result execute(String query, long batchOperations, long timeout) {
            Result result = new Result();
            Nanodbc.execute(result, this, query, batchOperations, timeout);
            return result;
        }
    }

    @Name("result")
    public static class Result extends Pointer {
        static {
            Loader.load();
        }

        public Result() {
            allocate();
        }

        private native void allocate();

        public native short columns();

        public native boolean next();
    }

    private static native void execute(@ByRef Result result, @ByRef Connection conn, String query, long batchOperations,
            long timeout);

}
