package com.spring.practice.my.app.coffee.adapter;

import com.spring.practice.my.app.coffee.Coffee;
import com.spring.practice.my.app.coffee.CoffeeType;
import com.spring.practice.my.app.coffee.ICoffees;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
class CoffeeController {

    private final ICoffees coffees;

    @GetMapping("/coffee/{name}")
    List<Coffee> findByName(@PathVariable String name){
        return coffees.findByName(name);
    }

    @PostMapping("/coffee")
    ResponseEntity<Coffee> registerCoffee(@RequestBody CoffeeRequest request){
        Coffee registered = coffees.register(
                Coffee.builder()
                        .name(request.name())
                        .coffeeType(CoffeeType.toEnum(request.typeCode())).build()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(registered);
    }
}
