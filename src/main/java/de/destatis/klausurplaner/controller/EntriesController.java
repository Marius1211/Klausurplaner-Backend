package de.destatis.klausurplaner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EntriesController {
 
    @GetMapping("/shoppinglist/entries")
    public ResponseEntity<?> getEntries() {
        return ResponseEntity.ok("");
    }
}
