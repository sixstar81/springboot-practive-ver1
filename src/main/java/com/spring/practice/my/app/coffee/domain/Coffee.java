package com.spring.practice.my.app.coffee.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 1. 커피 제품명
 * 2. 등록일자
 * 3. 제품 유형
 *    - 에스프레소 / 아메리카노 / 라떼
 */
@NoArgsConstructor
@Getter
@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime registered;
    private CoffeeType coffeeType;
    @Builder
    Coffee(String name, CoffeeType coffeeType){
        this.name = name;
        this.coffeeType = coffeeType;
        this.registered = LocalDateTime.now();
    }
}
