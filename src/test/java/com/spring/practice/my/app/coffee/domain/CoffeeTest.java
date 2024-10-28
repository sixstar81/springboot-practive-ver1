package com.spring.practice.my.app.coffee.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {

    @Test
    void invalidCreatedCoffee(){
        Assertions.assertThrowsExactly(IllegalStateException.class, () -> {
            Coffee.builder().coffeeType(CoffeeType.AMERICANO).build();
        });
    }

}