package com.lexicalunit.nanodbc;

public interface Statement extends AutoCloseable {

    Result execute(long batchOperations);

    @Override
    void close();

}
