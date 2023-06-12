package com.example.testgraphana;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestControllers {

    private MeterRegistry meterRegistry;
    private Counter counter;

    public TestControllers(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        counter =
                Counter.builder("get OK called")
                        .description("number of get OK called")
                        .register(meterRegistry);

    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @GetMapping("/ok")
    @Timed(value = "getOk.time", description = "Time taken to return OK")
    public String getOk() throws InterruptedException {
        counter.increment();
        Thread.sleep(getRandomNumber(100,1000));
        return "ok";
    }
}
