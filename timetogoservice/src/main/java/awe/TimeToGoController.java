package awe;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import awe.TimeLeft;

@RestController
public class TimeToGoController { 

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/timeleft")
    public TimeLeft greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new TimeLeft();
    }
}