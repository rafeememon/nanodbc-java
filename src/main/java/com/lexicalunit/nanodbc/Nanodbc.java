package com.lexicalunit.nanodbc;

import com.lexicalunit.nanodbc.impl.NativeConnection;

public class Nanodbc {

    public static Connection newConnection() {
        return new NativeConnection();
    }

    public static Connection newConnection(String connectionString, long timeout) {
        return new NativeConnection(connectionString, timeout);
    }

}
