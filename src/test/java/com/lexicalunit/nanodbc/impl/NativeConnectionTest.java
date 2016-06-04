package com.lexicalunit.nanodbc.impl;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NativeConnectionTest {

    @Before
    public void verifyJdbcConnection() throws SQLException {
        try (java.sql.Connection jdbc = NativeTestUtils.getJdbcConnection()) {
            Assert.assertTrue(jdbc.isValid(0));
        }
    }

    @Test
    public void testConnect() {
        try (NativeConnection connection = new NativeConnection()) {
            Assert.assertFalse(connection.connected());
            connection.connect(NativeTestUtils.getOdbcConnectionString(), 0L);
            Assert.assertTrue(connection.connected());
        }
    }

    @Test
    public void testDisconnect() {
        try (NativeConnection connection = new NativeConnection()) {
            connection.connect(NativeTestUtils.getOdbcConnectionString(), 0L);
            connection.disconnect();
            Assert.assertFalse(connection.connected());
        }
    }

}
