package com.lexicalunit.nanodbc.impl;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class NativeDateTimeTest {

    private static final int FRACT_TO_NANO_SCALAR = 100_000;

    @Test
    public void testFromLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        try (NativeDateTime nativeDateTime = NativeDateTime.fromLocalDateTime(localDateTime)) {
            Assert.assertEquals("year", localDateTime.getYear(), nativeDateTime.getYear());
            Assert.assertEquals("month", localDateTime.getMonthValue(), nativeDateTime.getMonth());
            Assert.assertEquals("day", localDateTime.getDayOfMonth(), nativeDateTime.getDay());
            Assert.assertEquals("hour", localDateTime.getHour(), nativeDateTime.getHour());
            Assert.assertEquals("minute", localDateTime.getMinute(), nativeDateTime.getMinute());
            Assert.assertEquals("second", localDateTime.getSecond(), nativeDateTime.getSecond());
            Assert.assertEquals("fract", localDateTime.getNano() / FRACT_TO_NANO_SCALAR, nativeDateTime.getFract());
        }
    }

    @Test
    public void testToLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        try (NativeDateTime nativeDateTime = new NativeDateTime()) {
            nativeDateTime.setYear(localDateTime.getYear());
            nativeDateTime.setMonth(localDateTime.getMonthValue());
            nativeDateTime.setDay(localDateTime.getDayOfMonth());
            nativeDateTime.setHour(localDateTime.getHour());
            nativeDateTime.setMinute(localDateTime.getMinute());
            nativeDateTime.setSecond(localDateTime.getSecond());
            nativeDateTime.setFract(localDateTime.getNano() / FRACT_TO_NANO_SCALAR);
            Assert.assertEquals(localDateTime, nativeDateTime.toLocalDateTime());
        }
    }

}
