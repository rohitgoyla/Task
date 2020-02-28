package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        //"20-Feb-2019"

/*        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

String dateInString = "7-Jun-2013";
Date date = formatter.parse(dateInString);*/
        LocalDateTime date =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(1550601000000L), ZoneId.systemDefault());
        System.out.println("date:: "+date);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        /*String date = "\"20-Feb-2019\"".replace("\"", "");
        try {
            System.out.println(formatter.parse(date));
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }*/

    }
}
