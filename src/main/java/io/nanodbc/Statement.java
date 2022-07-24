package io.nanodbc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Statement extends AutoCloseable {

    void bind(short column, int value);

    void bind(short column, long value);

    void bind(short column, float value);

    void bind(short column, double value);

    void bind(short column, String value);

    void bind(short column, LocalDate value);

    void bind(short column, LocalTime value);

    void bind(short column, LocalDateTime value);

    void bindNull(short column);

    Result execute();

    void justExecute();

    @Override
    void close();

}
