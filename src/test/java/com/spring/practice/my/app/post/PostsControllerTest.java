package com.spring.practice.my.app.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostsControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void find_all_posts() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/posts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}