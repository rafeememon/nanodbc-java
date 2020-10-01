package io.nanodbc;

public interface Transaction extends AutoCloseable {

    void commit();

    void rollback();

    @Override
    void close();

}
