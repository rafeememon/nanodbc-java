package io.nanodbc;

public interface Connection extends AutoCloseable {

    void connect(String connectionString, long timeout);

    void connect(String dsn, String user, String pass, long timeout);

    boolean connected();

    void disconnect();

    Result execute(String query, long timeout);

    Statement prepare(String query, long timeout);

    @Override
    void close();

}
