package com.speed.speeddemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SpeedService {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedService.class);

    @Cacheable(value = "fibonacci", key = "#n")
    public Long slowFibonacci(long n) {
        LOG.info("NOT CACHED so computing slow fibonacci: {}", n);
        if (n <= 1) {
            return n;
        } else {
            return slowFibonacci(n - 1) + slowFibonacci(n - 2);
        }
    }
}
