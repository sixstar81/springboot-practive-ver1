package com.spring.practice.my.app.coffee;

import com.spring.practice.my.app.coffee.model.CoffeeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoffeeTest {

    @Test
    void invalidCreatedCoffee(){
        Assertions.assertThrowsExactly(IllegalStateException.class, () -> {
            Coffee.builder().coffeeType(CoffeeType.AMERICANO).build();
        });
    }

    @Test
    void coffeeTypeCode(){
        String code = "001";
        CoffeeType result = CoffeeType.toEnum(code);
        assertThat(result.getText()).isEqualTo("에스프레소");
    }

    @Test
    void invalidCoffeeType(){
        String code = "999";
        Assertions.assertThrowsExactly(IllegalArgumentException.class, ()->{
            CoffeeType result = CoffeeType.toEnum(code);
        });
    }
}