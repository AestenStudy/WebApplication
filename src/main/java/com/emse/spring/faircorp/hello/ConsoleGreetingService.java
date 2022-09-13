package com.emse.spring.faircorp.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ConsoleGreetingService implements GreetingService{

    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
