package life.icetea.test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeTest {

    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime d1 = LocalDateTime.parse("2025-10-02 00:00:00", formatter);
        LocalDateTime d2 = LocalDateTime.parse("2026-07-14 23:59:59", formatter);
        // 加10天对比
        System.out.println(d1.format(formatter) + "==>" + d1.plusDays(10).format(formatter));
        System.out.println(d2.format(formatter) + "==>" + d2.plusDays(10).format(formatter));

        // 毫秒时间戳转localDateTime
        LocalDateTime d3 = LocalDateTime.ofInstant(Instant.ofEpochMilli(1759334400000L), ZoneId.systemDefault());
        LocalDateTime d4 = LocalDateTime.ofInstant(Instant.ofEpochMilli(1784044799000L), ZoneId.systemDefault());
        // 加10天对比
        System.out.println(d3.format(formatter) + "==>" + d1.plusDays(10).format(formatter));
        System.out.println(d4.format(formatter) + "==>" + d2.plusDays(10).format(formatter));

    }

}
