package de.destatis.klausurplaner.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import de.destatis.klausurplaner.dataTransferObjects.ExamDto;
import de.destatis.klausurplaner.entities.Exam;
import de.destatis.klausurplaner.repositories.ExamRepository;

@CrossOrigin
@RestController
public class EntriesController {
 
@Autowired
    private ExamRepository examRepository;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/exams")
    public ResponseEntity<List<ExamDto>> getEntries() {
    
        List<Exam> entries = examRepository.findAll();
    
        List<ExamDto> result = entries.stream()
            .map(entity -> new ExamDto(entity.getClassname(), entity.getSubject(), entity.getTopic()))
            .toList();
    
        return ResponseEntity.ok(result);
    }

    @PostMapping("/exams")
    public ResponseEntity<?> postEntry(@RequestBody ExamDto payload) {
        Exam entity = new Exam();
        entity.setClassname(payload.classname());
        entity.setSubject(payload.subject());
        entity.setTopic(payload.topic());
    
        entity = examRepository.save(entity);
    
        URI location = UriComponentsBuilder.fromUriString("/exams/{id}")
            .build(entity.getId());
    
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<ExamDto> getSingle(@PathVariable String id) {
        return examRepository.findById(id)
        .map(entity -> new ExamDto(entity.getClassname(), entity.getSubject(), entity.getTopic()))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
 
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        examRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/exams/{id}")
    public ResponseEntity<ExamDto> update(@PathVariable String id, @RequestBody ExamDto payload) {
        Optional<Exam> examEntry = examRepository.findById(id)
        .map(entity -> {
        entity.setClassname(payload.classname());
        entity.setSubject(payload.subject());
        entity.setTopic(payload.topic());
        return entity;
        });
        if (examEntry.isPresent()) {
        Exam savedEntity = examRepository.save(examEntry.get());
        return ResponseEntity.ok(new ExamDto(savedEntity.getClassname(), savedEntity.getSubject(), savedEntity.getTopic()));
        } else {
        return ResponseEntity.notFound().build();
        }
    }

}

