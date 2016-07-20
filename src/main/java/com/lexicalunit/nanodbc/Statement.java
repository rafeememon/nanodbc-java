package com.lexicalunit.nanodbc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Statement extends AutoCloseable {

    void bind(short column, int value);

    void bind(short column, float value);

    void bind(short column, double value);

    void bind(short column, String value);

    void bind(short column, LocalDate value);

    void bind(short column, LocalTime value);

    void bind(short column, LocalDateTime value);

    void bindNull(short column);

    Result execute(long batchOperations);

    @Override
    void close();

}
