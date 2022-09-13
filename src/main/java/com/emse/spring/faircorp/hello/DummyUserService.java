package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyUserService implements UserService {
    private GreetingService greetingService;

    @Autowired
    public DummyUserService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void greetAll() {
        for (String s : List.of("Elodie", "Charles")) {
            greetingService.greet(s);
        }
    }
}
