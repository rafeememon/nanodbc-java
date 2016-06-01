package com.lexicalunit.nanodbc;

public interface Connection extends AutoCloseable {

    void connect(String connectionString, long timeout);

    boolean connected();

    void disconnect();

    Result execute(String query, long batchOperations, long timeout);

    Statement prepare(String query, long timeout);

    @Override
    void close();

}
