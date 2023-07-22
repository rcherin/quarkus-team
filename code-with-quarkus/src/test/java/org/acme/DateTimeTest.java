package org.acme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {
    
    public static void main(String[] args) {    

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime t1 = LocalDateTime.parse("2023-01-20 23:01:02", formatter);
        System.out.println(t1);

    }
}
