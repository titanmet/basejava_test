package com.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DataUtil {
    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
