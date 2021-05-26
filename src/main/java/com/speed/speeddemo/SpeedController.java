package com.speed.speeddemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeedController {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedController.class);

    public SpeedController(){
        long fib = 48;

        LOG.info("Computing slow fibonacci: {}", fib);

        long fibResult = slowFibonacci(fib);

        LOG.info("slow fibonacci result: {}", fibResult);
    }

    @GetMapping("/")
    public double fast(){
        return Math.random() * 100;
    }

    public static long slowFibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return slowFibonacci(n - 1) + slowFibonacci(n - 2);
        }
    }

}
