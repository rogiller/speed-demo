package com.speed.speeddemo;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
public class SpeedController {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedController.class);

    final String VM_UUID = UUID.randomUUID().toString();

    private SpeedService speedService;

    public SpeedController(SpeedService speedService){
        this.speedService = speedService;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> fibonacci(
            @RequestParam(required = false, defaultValue = "1") long fib) throws UnknownHostException {
        Map<String, Object> result = new HashMap<>();
        result.put("vmUUID", VM_UUID);
        result.put("hostName", InetAddress.getLocalHost().getHostName());
        result.put("fibonacciInput", fib);
        result.put("vmUptime", getUptimeString());
        try{
            result.put("fibonacciResult", speedService.slowFibonacci(fib));
        }catch(Exception e){
            result.put("fibonacciResult", e.getMessage());
            LOG.error("", e);
        }
        LOG.info("Slow fibonacci result: {}", result.get("fibonacciResult"));
        return ok(result);
    }

    @GetMapping("/exit")
    public ResponseEntity<String> exit(){
        System.exit(0);
        return ok("JVM Exited!");
    }



    private static String getUptimeString() {
        RuntimeMXBean run = ManagementFactory.getRuntimeMXBean();
        return DurationFormatUtils.formatDuration(run.getUptime(), "d' day, 'H' hour, 'm' min, 's' sec'");
    }

}
