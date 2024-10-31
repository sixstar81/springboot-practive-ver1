package com.spring.practice.my.app.coffee.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.practice.my.app.coffee.Coffee;
import com.spring.practice.my.app.coffee.adapter.CoffeeController.CoffeeRequest;
import com.spring.practice.my.app.coffee.model.CoffeeDTO;
import com.spring.practice.my.app.coffee.model.CoffeeType;
import com.spring.practice.my.app.coffee.ICoffees;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoffeeController.class)
class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ICoffees iCoffees;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registerCoffee_ShouldReturnCreatedStatusAndCoffee() throws Exception {
        //given

        when(iCoffees.register(any())).thenReturn(new CoffeeDTO("Americano", LocalDateTime.now(), CoffeeType.ESPRESSO));
        // Arrange: 요청 객체 생성
        CoffeeRequest request = new CoffeeRequest("Americano", "001");
        String jsonRequest = objectMapper.writeValueAsString(request);

        // Act & Assert
        mockMvc.perform(post("/coffee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Americano"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.coffeeType").value("ESPRESSO"));
    }
}