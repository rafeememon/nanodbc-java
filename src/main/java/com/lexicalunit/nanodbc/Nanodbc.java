package com.lexicalunit.nanodbc;

import com.lexicalunit.nanodbc.impl.NativeConnection;

public class Nanodbc {

    public static Connection newConnection() {
        return new NativeConnection();
    }

    public static Connection newConnection(String connectionString, long timeout) {
        return new NativeConnection(connectionString, timeout);
    }

    public static Connection newConnection(String dsn, String user, String pass, long timeout) {
        return new NativeConnection(dsn, user, pass, timeout);
    }

}
