package com.example.mokinvaraus_backend.controllers;

import com.example.mokinvaraus_backend.models.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping(path = "/api")
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(path = "/greeting")
    public @ResponseBody Greeting greeting(@RequestParam(value = "name", defaultValue = "Mom") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
