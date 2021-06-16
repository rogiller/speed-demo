package com.speed.speeddemo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SpeedService {

    @Cacheable(value = "fibonacci", key = "#fib", unless = "#result == null")
    public Long slowFibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return slowFibonacci(n - 1) + slowFibonacci(n - 2);
        }
    }
}
