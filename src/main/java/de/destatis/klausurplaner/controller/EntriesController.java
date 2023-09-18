package de.destatis.klausurplaner.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import de.destatis.klausurplaner.dtos.ShoppinglistEntryDto;
import de.destatis.klausurplaner.entities.ShoppinglistEntry;
import de.destatis.klausurplaner.repositories.ShoppingListRepository;

@RestController
public class EntriesController {
    
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @GetMapping("/shoppinglist/entries")
    public ResponseEntity<List<ShoppinglistEntryDto>> getEntries() {
    
        List<ShoppinglistEntry> entries = shoppingListRepository.findAll();
    
        List<ShoppinglistEntryDto> result = entries.stream()
            .map(entity -> new ShoppinglistEntryDto(entity.getTitle(), entity.getAmount(), entity.getCategory()))
            .toList();
    
        return ResponseEntity.ok(result);
    }

    @PostMapping("/shoppinglist/entries")
    public ResponseEntity<?> postEntry(@RequestBody ShoppinglistEntryDto payload) {
        ShoppinglistEntry entity = new ShoppinglistEntry();
        entity.setTitle(payload.title());
        entity.setAmount(payload.amount());
        entity.setCategory(payload.category());
    
        entity = shoppingListRepository.save(entity);
    
        URI location = UriComponentsBuilder.fromUriString("/shoppinglist/entries/{id}")
            .build(entity.getId());
    
        return ResponseEntity.created(location).build();
    }
}

