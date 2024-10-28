package com.spring.practice.my.app.coffee.service;

import com.spring.practice.my.app.coffee.domain.Coffee;
import com.spring.practice.my.app.coffee.domain.CoffeeRepository;
import com.spring.practice.my.app.coffee.domain.CoffeeType;
import com.spring.practice.my.app.coffee.domain.ICoffee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Coffees implements ICoffee {

    private final CoffeeRepository coffeeRepository;
    @Override
    public Coffee register(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @Override
    public List<Coffee> findByName(String name) {
        return coffeeRepository.findByNameContaining(name).toList();
    }

    @Override
    public List<Coffee> findByType(CoffeeType type) {
        return coffeeRepository.findByType(type);
    }
}
