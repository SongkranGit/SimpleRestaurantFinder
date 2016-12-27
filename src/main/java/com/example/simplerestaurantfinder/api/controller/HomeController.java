package com.example.simplerestaurantfinder.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by BERM-PC on 27/12/2559.
 */

@RestController
public class HomeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Welcome to Simple Restaurant Finder System";
    }

}

