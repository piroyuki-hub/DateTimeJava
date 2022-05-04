import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @InjectMocks
    Main target;

    /** pattern */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSSS");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {}

    @Test
    void getCurrentDateTimeTest() {
        var actual = target.getCurrentDateTime(null, null);
        assertThat(actual).isNotNull();
        System.out.println(actual);
        System.out.println(actual.format(formatter));

        actual = target.getCurrentDateTime(Calendar.getInstance(), null);
        assertThat(actual).isNotNull();
        System.out.println(actual);

        // utc
        var utc = target.getCurrentDateTime(null, ZoneId.of("UTC"));
        assertThat(utc).isNotNull();
        System.out.printf("utc: %s\n", utc);

        // utc to jst
        var jst = ZonedDateTime.of(utc, ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.printf("jst: %s\n", jst.toLocalDateTime());
    }

    @Test
    void getDateTimeTest() {
        var actual = target.getDateTime(2022, 5, 2, 10, 30, 15);
        assertThat(actual)
                .isNotNull()
                .isEqualTo(LocalDateTime.of(2022, 5, 2, 10, 30, 15));
        System.out.println(actual);
    }

    @Test
    void parseTest() {
        var dateTime = target.getDateTime(2022, 5, 3, 3, 15, 25);
        var actual = target.convertStr2DateTime("20220503031525");
        assertThat(actual)
                .isNotNull()
                .isEqualTo(dateTime);
        System.out.println(actual);
    }
}
