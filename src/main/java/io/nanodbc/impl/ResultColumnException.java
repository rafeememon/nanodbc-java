package io.nanodbc.impl;

public class ResultColumnException extends RuntimeException {

    private static final long serialVersionUID = -2262922040771184772L;

    public ResultColumnException(String columnName, Throwable cause) {
        super("Error getting column: " + columnName, cause);
    }

    public ResultColumnException(short column, Throwable cause) {
        super("Error getting column index: " + column, cause);
    }

}
