package com.emse.spring.faircorp.hello;

import org.springframework.stereotype.Component;

@Component
public class AnotherConsoleGreetingService implements GreetingService{
    @Override
    public void greet(String name) {
        System.out.println("Bonjour, " + name + "!");
    }
}
