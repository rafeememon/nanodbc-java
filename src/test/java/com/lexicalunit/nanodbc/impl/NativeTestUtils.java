package com.lexicalunit.nanodbc.impl;

import java.sql.DriverManager;
import java.sql.SQLException;

public class NativeTestUtils {

    static java.sql.Connection getJdbcConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = String.format("jdbc:postgresql://%s:%d/%s", getPostgresHost(), getPostgresPort(),
                    getPostgresDatabase());
            return DriverManager.getConnection(url, getPostgresUser(), getPostgresPassword());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("could not initialize connection", e);
        }
    }

    static String getOdbcConnectionString() {
        return String.format("Driver={PostgreSQL ANSI(x64)};Server=%s;Port=%d;Database=%s;Uid=%s;Pwd=%s;",
                getPostgresHost(), getPostgresPort(), getPostgresDatabase(), getPostgresUser(), getPostgresPassword());
    }

    private static String getPostgresHost() {
        return System.getenv("PGHOST");
    }

    private static int getPostgresPort() {
        return Integer.parseInt(System.getenv("PGPORT"));
    }

    private static String getPostgresUser() {
        return System.getenv("PGUSER");
    }

    private static String getPostgresPassword() {
        return System.getenv("PGPASSWORD");
    }

    private static String getPostgresDatabase() {
        return System.getenv("PGDATABASE");
    }

}
