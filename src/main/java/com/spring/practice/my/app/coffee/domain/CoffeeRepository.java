package com.spring.practice.my.app.coffee.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Streamable<Coffee> findByNameContaining(String name);

    @Query("select u from #{#entityName} u where u.coffeeType = :type")
    List<Coffee> findByType(CoffeeType type);

}
