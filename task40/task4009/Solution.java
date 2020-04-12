package com.javarush.task.task40.task4009;

/* 
Buon Compleanno!
*/

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        System.out.println(getWeekdayOfBirthday("30.05.1984", "2015"));
    }

    public static String getWeekdayOfBirthday(String birthday, String year) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.yyyy", Locale.ITALIAN);
        LocalDate localDate = LocalDate.parse(birthday, dateTimeFormatter);
        localDate = localDate.with(Year.parse(year));
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALIAN).format(localDate).split(" ")[0];
    }
}
