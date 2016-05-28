package com.lexicalunit.nanodbc;

public interface Result extends AutoCloseable {

    short columns();

    boolean next();

    @Override
    void close();

}
