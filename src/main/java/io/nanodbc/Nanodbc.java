package io.nanodbc;

import io.nanodbc.impl.NativeConnection;

public class Nanodbc {
    static {
        Platform.checkPlatform();
    }

    public static Connection newConnection() {
        return new NativeConnection();
    }

    public static Connection newConnection(String connectionString) {
        return new NativeConnection(connectionString);
    }

    public static Connection newConnection(String connectionString, long timeout) {
        return new NativeConnection(connectionString, timeout);
    }

    public static Connection newConnection(String dsn, String user, String pass, long timeout) {
        return new NativeConnection(dsn, user, pass, timeout);
    }

}
