package com.spring.practice.my.app.coffee.domain;

import lombok.Getter;

@Getter
public enum CoffeeType {

    ESPRESSO("에스프레소"), AMERICANO("아메리카노"), LATTE("라떼");

    private final String text;

    CoffeeType(String text) {
        this.text = text;
    }
}