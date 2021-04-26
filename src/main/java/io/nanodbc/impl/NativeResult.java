package io.nanodbc.impl;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.StdString;

import io.nanodbc.Result;

@Platform(include = "nanodbc_ext.h", library = "jninanodbc")
@Namespace("nanodbc")
@Name("result")
public class NativeResult extends Pointer implements Result {
    static {
        Loader.load();
    }

    public NativeResult() {
        allocate();
    }

    private native void allocate();

    @Override
    public native boolean next();

    @Override
    @Name("columns")
    public native short getNumColumns();

    @Override
    @Name("affected_rows")
    public native long getAffectedRows();

    @Override
    public String getColumnName(short column) {
        try {
            return getColumnNameNative(column);
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public JDBCType getColumnType(short column) {
        try {
            return JDBCType.valueOf(getColumnDatatypeNative(column));
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public JDBCType getColumnType(String columnName) {
        try {
            return JDBCType.valueOf(getColumnDatatypeNative(columnName));
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public boolean isNull(short column) {
        try {
            return isNullNative(column);
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public boolean isNull(String columnName) {
        try {
            return isNullNative(columnName);
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public int getInt(short column) {
        try {
            return getIntNative(column);
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public int getInt(String columnName) {
        try {
            return getIntNative(columnName);
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public float getFloat(short column) {
        try {
            return getFloatNative(column);
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public float getFloat(String columnName) {
        try {
            return getFloatNative(columnName);
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public double getDouble(short column) {
        try {
            return getDoubleNative(column);
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public double getDouble(String columnName) {
        try {
            return getDoubleNative(columnName);
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public String getString(short column) {
        try {
            return getStringNative(column);
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public String getString(String columnName) {
        try {
            return getStringNative(columnName);
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public LocalDate getDate(short column) {
        try (NativeDate date = new NativeDate()) {
            getDateRefNative(column, date);
            return date.toLocalDate();
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public LocalDate getDate(String columnName) {
        try (NativeDate date = new NativeDate()) {
            getDateRefNative(columnName, date);
            return date.toLocalDate();
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public LocalTime getTime(short column) {
        try (NativeTime time = new NativeTime()) {
            getTimeRefNative(column, time);
            return time.toLocalTime();
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public LocalTime getTime(String columnName) {
        try (NativeTime time = new NativeTime()) {
            getTimeRefNative(columnName, time);
            return time.toLocalTime();
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Override
    public LocalDateTime getDateTime(short column) {
        try (NativeDateTime dateTime = new NativeDateTime()) {
            getDateTimeRefNative(column, dateTime);
            return dateTime.toLocalDateTime();
        } catch (Exception e) {
            throw new ResultColumnException(column, e);
        }
    }

    @Override
    public LocalDateTime getDateTime(String columnName) {
        try (NativeDateTime dateTime = new NativeDateTime()) {
            getDateTimeRefNative(columnName, dateTime);
            return dateTime.toLocalDateTime();
        } catch (Exception e) {
            throw new ResultColumnException(columnName, e);
        }
    }

    @Name("column_name")
    private native @StdString String getColumnNameNative(short column);

    @Name("column_datatype")
    private native int getColumnDatatypeNative(short column);

    @Name("column_datatype")
    private native int getColumnDatatypeNative(String columnName);

    @Name("is_null")
    private native boolean isNullNative(short column);

    @Name("is_null")
    private native boolean isNullNative(String columnName);

    @Name("get<int32_t>")
    private native int getIntNative(short column);

    @Name("get<int32_t>")
    private native int getIntNative(String columnName);

    @Name("get<float>")
    private native float getFloatNative(short column);

    @Name("get<float>")
    private native float getFloatNative(String columnName);

    @Name("get<double>")
    private native double getDoubleNative(short column);

    @Name("get<double>")
    private native double getDoubleNative(String columnName);

    @Name("get<::nanodbc::string>")
    private native @StdString String getStringNative(short column);

    @Name("get<::nanodbc::string>")
    private native @StdString String getStringNative(String columnName);

    @Name("get_ref<::nanodbc::date>")
    private native void getDateRefNative(short column, @ByRef NativeDate date);

    @Name("get_ref<::nanodbc::date>")
    private native void getDateRefNative(String columnName, @ByRef NativeDate date);

    @Name("get_ref<::nanodbc::time>")
    private native void getTimeRefNative(short column, @ByRef NativeTime time);

    @Name("get_ref<::nanodbc::time>")
    private native void getTimeRefNative(String columnName, @ByRef NativeTime time);

    @Name("get_ref<::nanodbc::timestamp>")
    private native void getDateTimeRefNative(short column, @ByRef NativeDateTime date);

    @Name("get_ref<::nanodbc::timestamp>")
    private native void getDateTimeRefNative(String columnName, @ByRef NativeDateTime date);

}
