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
        mockMvc.perform(MockMvcRequestBuilders.get("/exam/entries"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEntriesGetIsAvailableAndReturnsData() throws Exception {
        mockMvc.perform(get("/exam/entries"))
            .andExpect(status().isOk()) //ist HTTP-Status 200
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(2))) //erwartet ein Array das 2 Elemente beinhaltet
    
                //ueberpruefung aller Eigenschaften des ersten Elements
            .andExpect(jsonPath("$[0].classname", is("12ITa")))
            .andExpect(jsonPath("$[0].subject", is("Ethik")))
            .andExpect(jsonPath("$[0].topic", is("Moral")))
    
                //ueberpruefung aller Eigenschaften des zweiten Elements
            .andExpect(jsonPath("$[1].classname", is("12ITa")))
            .andExpect(jsonPath("$[1].subject", is("Sport")))
            .andExpect(jsonPath("$[1].topic", is("Basketball")));
    }

    @Test
    public void testAddEntry() throws Exception {
        //Ueberprueft die Anzahl der Eintraege
        mockMvc.perform(get("/exam/entries"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    
        //Erstellt einen neuen Eintrag
        String payload = """
            {
                "classname": "12ITc",
                "subject": "Deutsch",
                "topic": "Bewerbung schreiben"
            }
            """;
    
        //Fuegt den neuen Eintrag hinzu
        mockMvc.perform(post("/exam/entries")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    
        //Ueberprueft, ob sich Anzahl der Eintraege veraendert hat
        mockMvc.perform(get("/exam/entries"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
                //Ueberprueft die Eigenschaften des neuen Eintrags
            .andExpect(jsonPath("$[2].classname", is("12ITc")))
            .andExpect(jsonPath("$[2].subject", is("Deutsch")))
            .andExpect(jsonPath("$[2].topic", is("Bewerbung schreiben")));
    }

    @Test
    public void testReadSingle() throws Exception {
    mockMvc.perform(get("/exam/entries/2"))
    .andExpect(status().isOk())
        .andExpect(jsonPath("$[2].classname", is("12ITc")))
        .andExpect(jsonPath("$[2].subject", is("Deutsch")))
        .andExpect(jsonPath("$[2].topic", is("Bewerbung schreiben")));
    }

}