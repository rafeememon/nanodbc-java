package com.lexicalunit;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "nanodbc.h")
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
    }

}
