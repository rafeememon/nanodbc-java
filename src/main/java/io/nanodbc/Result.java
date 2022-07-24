package io.nanodbc;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface Result extends AutoCloseable {

    boolean next();

    short getNumColumns();

    long getAffectedRows();

    String getColumnName(short column);

    JDBCType getColumnType(short column);

    JDBCType getColumnType(String columnName);

    boolean isNull(short column);

    boolean isNull(String columnName);

    int getInt(short column);

    int getInt(String columnName);

    long getLong(short column);

    long getLong(String columnName);

    float getFloat(short column);

    float getFloat(String columnName);

    double getDouble(short column);

    double getDouble(String columnName);

    String getString(short column);

    String getString(String columnName);

    LocalDate getDate(short column);

    LocalDate getDate(String columnName);

    LocalTime getTime(short column);

    LocalTime getTime(String columnName);

    LocalDateTime getDateTime(short column);

    LocalDateTime getDateTime(String columnName);

    @Override
    void close();

}
