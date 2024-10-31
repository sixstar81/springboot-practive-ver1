package com.spring.practice.my.app.coffee.adapter;

import com.spring.practice.my.app.coffee.Coffee;
import com.spring.practice.my.app.coffee.model.CoffeeDTO;
import com.spring.practice.my.app.coffee.model.CoffeeType;
import com.spring.practice.my.app.coffee.ICoffees;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
class CoffeeController {

    private final ICoffees coffees;

    @GetMapping("/coffee/{name}")
    ResponseEntity<List<Coffee>> findByName(@PathVariable String name){
        return ResponseEntity.ok(coffees.findByName(name));
    }

    @PostMapping("/coffee")
    ResponseEntity<CoffeeDTO> registerCoffee(@RequestBody CoffeeRequest request){
        CoffeeDTO registered = coffees.register(
                Coffee.builder()
                        .name(request.name())
                        .coffeeType(CoffeeType.toEnum(request.typeCode())).build()
        );
        //return ResponseEntity.status(HttpStatus.CREATED).body(registered);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    static record CoffeeRequest(String name, String typeCode) {
    }
}
