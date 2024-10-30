package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.LocalTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

// BEGIN

// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @RequestScope
    public Daytime getBeanOnTime() {
        LocalTime startDayTime = LocalTime.of(6, 0, 0);
        LocalTime endDayTime = LocalTime.of(22, 0, 0);
        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(startDayTime) && currentTime.isBefore(endDayTime)) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
