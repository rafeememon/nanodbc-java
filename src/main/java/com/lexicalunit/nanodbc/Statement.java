package com.lexicalunit.nanodbc;

public interface Statement extends AutoCloseable {

    void bind(short column, int value);

    void bind(short column, float value);

    void bind(short column, double value);

    void bind(short column, String value);

    void bindNull(short column);

    Result execute(long batchOperations);

    @Override
    void close();

}
