package com.spring.practice.my.app.coffee.domain;

import java.util.List;

/**
 * 1. 커피 제품 등록 하기
 * 2. 커피 제품 조회 하기
 *     - ID로 조회하기
 *     - 커피 제품명으로 조회하기
 *     - 제품 유형으로 조회하기
 */
public interface ICoffee {
    Coffee register(Coffee coffee);
    Coffee findById(Long id);
    List<Coffee> findByName(String name);
    List<Coffee> findByType(CoffeeType type);
}