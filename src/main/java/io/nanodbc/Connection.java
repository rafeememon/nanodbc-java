package io.nanodbc;

public interface Connection extends AutoCloseable {

    void connect(String connectionString);

    void connect(String connectionString, long timeout);

    void connect(String dsn, String user, String pass, long timeout);

    boolean connected();

    void disconnect();

    Result execute(String query);

    Result execute(String query, long timeout);

    void justExecute(String query);

    void justExecute(String query, long timeout);

    Statement prepare(String query);

    Statement prepare(String query, long timeout);

    Transaction beginTransaction();

    @Override
    void close();

}
