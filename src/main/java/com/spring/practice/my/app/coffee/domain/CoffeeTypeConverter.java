package com.spring.practice.my.app.coffee.domain;

import jakarta.persistence.AttributeConverter;

public class CoffeeTypeConverter implements AttributeConverter<CoffeeType, String> {
    @Override
    public String convertToDatabaseColumn(CoffeeType type) {

        return null;
    }

    @Override
    public CoffeeType convertToEntityAttribute(String s) {
        return null;
    }
}
