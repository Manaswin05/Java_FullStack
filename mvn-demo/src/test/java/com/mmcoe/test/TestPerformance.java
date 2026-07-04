package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TestPerformance {

    @Test
    @Timeout(value = 5, unit = TimeUnit.MILLISECONDS)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(1);
    }

    @Test
    public void testAssertTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(9);
        });
    }
}