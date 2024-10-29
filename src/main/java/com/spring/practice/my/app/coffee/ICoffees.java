package com.spring.practice.my.app.coffee;

import java.util.List;

/**
 * 1. 커피 제품 등록 하기
 * 2. 커피 제품 조회 하기
 *     - ID로 조회하기
 *     - 커피 제품명으로 조회하기
 *     - 제품 유형으로 조회하기
 */
public interface ICoffees {
    Coffee register(Coffee coffee);
    List<Coffee> findByName(String name);
    List<Coffee> findByType(CoffeeType type);
}