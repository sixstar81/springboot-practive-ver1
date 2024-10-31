package com.spring.practice.my.app.coffee.model;

import java.time.LocalDateTime;

public record CoffeeDTO(String name, LocalDateTime registered, CoffeeType coffeeType) {
}
