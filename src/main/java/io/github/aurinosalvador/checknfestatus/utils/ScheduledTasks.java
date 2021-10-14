package io.github.aurinosalvador.checknfestatus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Aurino Salvador
 */

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class.getName());

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1)
    public void getSefazNfeStatus() {
        Date now = new Date();

        log.info("Data Atual: {}", now);

    }
}
