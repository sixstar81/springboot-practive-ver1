package com.spring.practice.my.app.coffee.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    Streamable<Coffee> findByNameContaining(String name);
}
