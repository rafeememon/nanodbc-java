package com.lexicalunit.nanodbc.impl;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class NativeDateTest {

    @Test
    public void testFromLocalDate() {
        LocalDate localDate = LocalDate.now();
        try (NativeDate nativeDate = NativeDate.fromLocalDate(localDate)) {
            Assert.assertEquals("year", localDate.getYear(), nativeDate.getYear());
            Assert.assertEquals("month", localDate.getMonthValue(), nativeDate.getMonth());
            Assert.assertEquals("day", localDate.getDayOfMonth(), nativeDate.getDay());
        }
    }

    @Test
    public void testToLocalDate() {
        LocalDate localDate = LocalDate.now();
        try (NativeDate nativeDate = new NativeDate()) {
            nativeDate.setYear(localDate.getYear());
            nativeDate.setMonth(localDate.getMonthValue());
            nativeDate.setDay(localDate.getDayOfMonth());
            Assert.assertEquals(localDate, nativeDate.toLocalDate());
        }
    }

}
