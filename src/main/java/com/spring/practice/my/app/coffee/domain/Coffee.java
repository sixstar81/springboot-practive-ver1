package com.spring.practice.my.app.coffee.domain;

import java.time.LocalDateTime;

/**
 * 1. 커피 제품명
 * 2. 등록일자
 * 3. 제품 유형
 *    - 에스프레소 / 아메리카노 / 라떼
 */
public class Coffee {
    private String name;
    private LocalDateTime registered;
    private CoffeeType coffeeType;
}
