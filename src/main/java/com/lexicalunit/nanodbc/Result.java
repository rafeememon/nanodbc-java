package com.lexicalunit.nanodbc;

import java.sql.JDBCType;
import java.time.LocalDate;

public interface Result extends AutoCloseable {

    boolean next();

    short getNumColumns();

    String getColumnName(short column);

    JDBCType getColumnType(short column);

    JDBCType getColumnType(String columnName);

    boolean isNull(short column);

    boolean isNull(String columnName);

    int getInt(short column);

    int getInt(String columnName);

    float getFloat(short column);

    float getFloat(String columnName);

    double getDouble(short column);

    double getDouble(String columnName);

    String getString(short column);

    String getString(String columnName);

    LocalDate getDate(short column);

    LocalDate getDate(String columnName);

    @Override
    void close();

}
