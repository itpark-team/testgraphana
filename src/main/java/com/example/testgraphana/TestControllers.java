package com.example.testgraphana;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestControllers {
    @GetMapping("/ok")
    public String getOk(){
        return "ok";
    }
}
