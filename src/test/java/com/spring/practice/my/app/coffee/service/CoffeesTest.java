package com.spring.practice.my.app.coffee.service;

import com.spring.practice.my.app.coffee.domain.Coffee;
import com.spring.practice.my.app.coffee.domain.CoffeeRepository;
import com.spring.practice.my.app.coffee.domain.CoffeeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.spring.practice.my.app.coffee.domain.CoffeeType.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoffeesTest {

    @Autowired
    Coffees coffees;

    @Autowired
    CoffeeRepository coffeeRepository;

    @BeforeEach
    void init(){
        coffeeRepository.deleteAllInBatch();
    }

    @Test
    void registerCoffee(){
        Coffee coffee = createCoffee("ice-americano", AMERICANO);
        Coffee register = coffees.register(coffee);
        Assertions.assertThat(register.getId()).isNotNull();
    }

    private static Coffee createCoffee(String name, CoffeeType type) {
        return Coffee.builder().name(name).coffeeType(type).build();
    }

}