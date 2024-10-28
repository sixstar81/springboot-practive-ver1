package com.spring.practice.my.app.coffee.domain;

import jakarta.persistence.AttributeConverter;

public class CoffeeTypeConverter implements AttributeConverter<CoffeeType, String> {
    @Override
    public String convertToDatabaseColumn(CoffeeType type) {
        return type.getCode();
    }

    @Override
    public CoffeeType convertToEntityAttribute(String code) {
        return CoffeeType.toEnum(code);
    }
}
