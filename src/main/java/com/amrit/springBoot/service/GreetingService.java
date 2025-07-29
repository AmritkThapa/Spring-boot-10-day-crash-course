package com.amrit.springBoot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service // This annotation indicates that this class is a service component in the Spring context.
public class GreetingService {
    @Value("${greeting.message}") //
    private String message;

    public String greet(String name) {
        return message + "Hello, " + name;
    }
}
