package de.destatis.klausurplaner.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import de.destatis.klausurplaner.dtos.ExamlistEntryDto;
import de.destatis.klausurplaner.entities.ExamlistEntry;
import de.destatis.klausurplaner.repositories.ExamlistRepository;

@RestController
public class EntriesController {
 
@Autowired
    private ExamlistRepository examlistRepository;

    @GetMapping("/examlist/entries")
    public ResponseEntity<List<ExamlistEntryDto>> getEntries() {
    
        List<ExamlistEntry> entries = examlistRepository.findAll();
    
        List<ExamlistEntryDto> result = entries.stream()
            .map(entity -> new ExamlistEntryDto(entity.getTitle(), entity.getAmount(), entity.getCategory()))
            .toList();
    
        return ResponseEntity.ok(result);
    }

    @PostMapping("/examlist/entries")
    public ResponseEntity<?> postEntry(@RequestBody ExamlistEntryDto payload) {
        ExamlistEntry entity = new ExamlistEntry();
        entity.setTitle(payload.title());
        entity.setAmount(payload.amount());
        entity.setCategory(payload.category());
    
        entity = examlistRepository.save(entity);
    
        URI location = UriComponentsBuilder.fromUriString("/exam/entries/{id}")
            .build(entity.getId());
    
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/examlist/entries/{id}")
    public ResponseEntity<ExamlistEntryDto> getSingle(@PathVariable String id) {
    return ExamlistRepository.findById(id)
    .map(entity -> new ExamlistEntryDto(entity.getTitle(), entity.getAmount(), entity.getCategory()))
    .map(ResponseEntity::ok)
    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/examlist/entries/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        ExamlistRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/examlist/entries/{id}")
    public ResponseEntity<ExamlistEntryDto> update(@PathVariable String id, @RequestBody ExamlistEntryDto payload) {
        Optional<ExamlistEntry> examlistEntry = ExamlistRepository.findById(id)
        .map(entity -> {
        entity.setTitle(payload.title());
        entity.setAmount(payload.amount());
        entity.setCategory(payload.category());
        return entity;
        });
        if (examlistEntry.isPresent()) {
        ExamlistEntry savedEntity = examlistRepository.save(examlistEntry.get());
        return ResponseEntity.ok(new ExamlistEntryDto(savedEntity.getTitle(), savedEntity.getAmount(), savedEntity.getCategory()));
        } else {
        return ResponseEntity.notFound().build();
        }
    }

}

