import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

public class Main {

    /**
     * 現在日時を取得.
     *
     * @param cal {@link Calendar}
     * @param zone {@link ZoneId}
     * @return {@link LocalDateTime}
     */
    LocalDateTime getCurrentDateTime(Calendar cal, ZoneId zone) {
        ZoneId zoneId = Objects.isNull(zone) ? ZoneId.systemDefault() : zone;
        if (Objects.isNull(cal)) return LocalDateTime.now(zoneId);

        // NOTE Instant: エポック秒を表す
        return LocalDateTime.ofInstant(cal.toInstant(), zoneId);
    }

    /**
     * 日時を指定して取得.
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @param hour 時
     * @param minute 分
     * @param second 秒
     * @return {@link LocalDateTime}
     */
    LocalDateTime getDateTime(int year, int month, int day, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * 文字列を日時に変換.
     *
     * @param datetime 日付 (yyyyMMddHHmmss)
     * @return {@link LocalDateTime}
     */
    LocalDateTime convertStr2DateTime(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.parse(datetime, formatter);
    }
}
