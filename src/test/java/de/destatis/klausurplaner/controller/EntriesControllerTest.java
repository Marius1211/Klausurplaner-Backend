package de.destatis.klausurplaner.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class EntriesControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Test
    public void testEntriesGetIsAvailable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/shoppinglist/entries"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}