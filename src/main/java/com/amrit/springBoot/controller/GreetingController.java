package com.amrit.springBoot.controller;

import com.amrit.springBoot.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation indicates that this class is a REST controller, which handles HTTP requests and responses.
@RequestMapping("/api") // This annotation maps HTTP requests to the specified path, in this case, "/greeting".
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("/hello") // This annotation maps HTTP GET requests to the "/hello" endpoint.
    public String greet(@RequestParam(defaultValue = "Guest") String name) { // This method handles GET requests to "/greeting/hello" and accepts a query parameter 'name' with a default value of "Guest".
        return greetingService.greet(name);
    }
}
