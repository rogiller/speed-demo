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
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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

    @GetMapping("/java17")
    public ResponseEntity<Book> java17(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author){

        Book book = new Book(title, author);

        return ok(book);
    }

    @GetMapping("/eatMemory")
    public void eatAllMemory(){
        LOG.info("Beginning to eat all available memory...");
        Vector v = new Vector();
        while (true) {
            byte [] b  = new byte[1048576];
            v.add(b);
            Runtime rt = Runtime.getRuntime();
            LOG.info("Free memory: " + rt.freeMemory());
        }
    }

    private static Vector staticVector = new Vector();
    @GetMapping("/eatMemoryStatic")
    public void eatAllMemoryStatic(){
        LOG.info("Beginning to eat all available memory...");

        while (true) {
            byte [] b  = new byte[1048576];
            staticVector.add(b);
            Runtime rt = Runtime.getRuntime();
            LOG.info("Free memory: " + rt.freeMemory());
        }
    }

    @GetMapping("/exit")
    public ResponseEntity<String> exit(){
        System.exit(0);
        return ok("JVM Exited!");
    }

    @GetMapping("/mysql")
    public List<String> remoteMysql() throws ClassNotFoundException {

        List<String> stringList = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        String jdbcUrl = System.getenv("SPEED_DEMO_JDBC_URL");
        String jdbcPassword = System.getenv("SPEED_DEMO_JDBC_PASSWORD");

        try(Connection con= DriverManager.getConnection(
                jdbcUrl,"root", jdbcPassword)){

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from st_biz_location");

            while(rs.next()){
                stringList.add(rs.getString("id"));
            }

        }catch(Exception e){
            LOG.error("", e);
        }

        return stringList;
    }

    private static String getUptimeString() {
        RuntimeMXBean run = ManagementFactory.getRuntimeMXBean();
        return DurationFormatUtils.formatDuration(run.getUptime(), "d' day, 'H' hour, 'm' min, 's' sec'");
    }

}
