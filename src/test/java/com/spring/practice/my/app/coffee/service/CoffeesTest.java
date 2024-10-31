package com.spring.practice.my.app.coffee.service;

import com.spring.practice.my.app.coffee.Coffee;
import com.spring.practice.my.app.coffee.CoffeeRepository;
import com.spring.practice.my.app.coffee.model.CoffeeType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.spring.practice.my.app.coffee.model.CoffeeType.*;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@SpringBootTest
class CoffeesTest {

    @Autowired
    Coffees coffees;

    @Autowired
    CoffeeRepository coffeeRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init(){
        coffeeRepository.deleteAllInBatch();
    }

    @Test
    void registerCoffee(){
        Coffee coffee = createCoffee("ice-americano", AMERICANO);
        coffees.register(coffee);

        //coffeeType이 code값으로 잘 저장되는지 확인
        jdbcTemplate.query("select * from coffee", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println(rs.getString("coffee_type"));
                return 0;
            }
        });

        Optional<Coffee> byId = coffeeRepository.findById(1L);
        System.out.println(byId.get());
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

    @Test
    void findByType(){
        //given
        coffeeLoads();
        //when
        List<Coffee> byType = coffees.findByType(AMERICANO);
        Assertions.assertThat(byType).hasSize(3)
                .extracting("coffeeType")
                .containsExactlyInAnyOrder(AMERICANO, AMERICANO, AMERICANO);
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