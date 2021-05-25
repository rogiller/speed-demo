package com.speed.speeddemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeedController {

    @GetMapping("/")
    public double fast(){
        return Math.random() * 100;
    }

}
