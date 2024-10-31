package com.spring.practice.my.app.coffee.model;

import lombok.Getter;

@Getter
public enum CoffeeType {

    ESPRESSO("에스프레소", "001"), AMERICANO("아메리카노", "002"), LATTE("라떼", "003");

    private final String text;
    private final String code;

    CoffeeType(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public static CoffeeType toEnum(String code){
        for(CoffeeType t:values()){
            if(t.code.equals(code))
                return t;
        }
        throw new IllegalArgumentException("not exists coffeeType code");
    }

}