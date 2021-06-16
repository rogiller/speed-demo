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
        return slowFibonacciCompute(n);
    }

    public Long slowFibonacciCompute(long n) {
        if (n <= 1) {
            return n;
        } else {
            return slowFibonacciCompute(n - 1) + slowFibonacciCompute(n - 2);
        }
    }
}
