package com.spring.practice.my.app.coffee.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.util.StringUtils.hasText;

/**
 * 1. 커피 제품명
 * 2. 등록일자
 * 3. 제품 유형
 *    - 에스프레소 / 아메리카노 / 라떼
 */
@ToString
@NoArgsConstructor
@Getter
@Entity
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime registered;
    //@Enumerated(EnumType.STRING) //생략하면 EnumType.ORDINAL(순번)으로 기본설정
    @Convert(converter = CoffeeTypeConverter.class)
    private CoffeeType coffeeType;
    @Builder
    Coffee(String name, CoffeeType coffeeType){

        boolean validCoffee = hasText(name) && Objects.nonNull(coffeeType);
        if(!validCoffee) throw new IllegalStateException("유효하지 않은 커피 생성입니다.");

        this.name = name;
        this.coffeeType = coffeeType;
        this.registered = LocalDateTime.now();

    }
}
