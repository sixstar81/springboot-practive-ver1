package com.spring.practice.my.app.coffee.service;

import com.spring.practice.my.app.coffee.Coffee;
import com.spring.practice.my.app.coffee.CoffeeRepository;
import com.spring.practice.my.app.coffee.model.CoffeeDTO;
import com.spring.practice.my.app.coffee.model.CoffeeType;
import com.spring.practice.my.app.coffee.ICoffees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class Coffees implements ICoffees {

    private final CoffeeRepository coffeeRepository;
    @Override
    public CoffeeDTO register(Coffee coffee) {
        return mapToDTO(coffeeRepository.save(coffee));
    }

    @Override
    public List<Coffee> findByName(String name) {
        return coffeeRepository.findByNameContaining(name).toList();
    }

    @Override
    public List<Coffee> findByType(CoffeeType type) {
        return coffeeRepository.findByType(type);
    }

    private CoffeeDTO mapToDTO(Coffee coffee){
        return new CoffeeDTO(coffee.getName(), coffee.getRegistered(), coffee.getCoffeeType());
    }

}
