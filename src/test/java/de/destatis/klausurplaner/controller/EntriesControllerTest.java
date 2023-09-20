package de.destatis.klausurplaner.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.transaction.Transactional;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:setup-testdata.sql")
@Transactional
public class EntriesControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Test
    public void testEntriesGetIsAvailable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/examlist/entries"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEntriesGetIsAvailableAndReturnsData() throws Exception {
        mockMvc.perform(get("/examlist/entries"))
            .andExpect(status().isOk()) //ist HTTP-Status 200
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2))) //erwartet ein Array das 2 Elemente beinhaltet
    
                //ueberpruefung aller Eigenschaften des ersten Elements
            .andExpect(jsonPath("$[0].title", is("Butter")))
            .andExpect(jsonPath("$[0].amount", is(2)))
            .andExpect(jsonPath("$[0].category", is("dairy")))
    
                //ueberpruefung aller Eigenschaften des zweiten Elements
            .andExpect(jsonPath("$[1].title", is("Potatoes")))
            .andExpect(jsonPath("$[1].amount", is(5)))
            .andExpect(jsonPath("$[1].category", is("vegetables")));
    }

    @Test
    public void testAddEntry() throws Exception {
        //Ueberprueft die Anzahl der Eintraege
        mockMvc.perform(get("/examlist/entries"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    
        //Erstellt einen neuen Eintrag
        String payload = """
            {
                "title": "Tomatoes",
                "amount": 4,
                "category": "vegetables"
            }
            """;
    
        //Fuegt den neuen Eintrag hinzu
        mockMvc.perform(post("/examlist/entries")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    
        //Ueberprueft, ob sich Anzahl der Eintraege veraendert hat
        mockMvc.perform(get("/examlist/entries"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
                //Ueberprueft die Eigenschaften des neuen Eintrags
            .andExpect(jsonPath("$[2].title", is("Tomatoes")))
            .andExpect(jsonPath("$[2].amount", is(4)))
            .andExpect(jsonPath("$[2].category", is("vegetables")));
    }

    @Test
    public void testReadSingle() throws Exception {
    mockMvc.perform(get("/examlist/entries/2"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.title", is("Potatoes")))
    .andExpect(jsonPath("$.amount", is(5)))
    .andExpect(jsonPath("$.category", is("vegetables")));
    }

}