package com.spring.practice.my.app.coffee.service;

import com.spring.practice.my.app.coffee.domain.Coffee;
import com.spring.practice.my.app.coffee.domain.CoffeeRepository;
import com.spring.practice.my.app.coffee.domain.CoffeeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.spring.practice.my.app.coffee.domain.CoffeeType.*;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
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

    @Test
    void findByName(){
        //given
        coffeeLoads();
        //when
        List<Coffee> iceCoffees = coffees.findByName("ice");
        //then
        Assertions.assertThat(iceCoffees).hasSize(3)
                .extracting("name", "coffeeType")
                .containsExactlyInAnyOrder(
                        tuple("ice-americano", AMERICANO),
                        tuple("ice-espresso", ESPRESSO),
                        tuple("ice-latte", LATTE)
                );
    }

    private void coffeeLoads() {
        Coffee coffee1 = createCoffee("ice-americano", AMERICANO);
        Coffee coffee2 = createCoffee("hot-americano", AMERICANO);
        Coffee coffee3 = createCoffee("decaffein-americano", AMERICANO);
        Coffee coffee4 = createCoffee("ice-espresso", ESPRESSO);
        Coffee coffee5 = createCoffee("hot-espresso", ESPRESSO);
        Coffee coffee6 = createCoffee("ice-latte", LATTE);

        coffeeRepository.saveAll(List.of(coffee1, coffee2, coffee3, coffee4, coffee5, coffee6));
    }

    private static Coffee createCoffee(String name, CoffeeType type) {
        return Coffee.builder().name(name).coffeeType(type).build();
    }

}