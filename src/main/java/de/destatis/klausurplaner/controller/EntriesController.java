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

import de.destatis.klausurplaner.dataTransferObjects.CalendarDto;
import de.destatis.klausurplaner.entities.Calendar;
import de.destatis.klausurplaner.repositories.CalendarRepository;

import de.destatis.klausurplaner.dataTransferObjects.KlasseDto;
import de.destatis.klausurplaner.entities.Klasse;
import de.destatis.klausurplaner.repositories.KlasseRepository;

@CrossOrigin
@RestController
public class EntriesController {
 
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private KlasseRepository klasseRepository;


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/exams")
    public ResponseEntity<List<ExamDto>> getEntries() {
    
        List<Exam> entries = examRepository.findAll();
    
        List<ExamDto> result = entries.stream()
            .map(entity -> new ExamDto(entity.getId(), entity.getClassname(), entity.getStunde(), entity.getSubject(), entity.getTopic(), entity.getSontiges()))
            .toList();
    
        return ResponseEntity.ok(result);
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarDto>> getEntriesCalendar() {

        List<Calendar> entries = calendarRepository.findAll();

        List<CalendarDto> result = entries.stream()
                .map(entity -> new CalendarDto(entity.getSchulstunde(), entity.getTag(), entity.getKlausurArt()))
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/klasse")
    public ResponseEntity<List<KlasseDto>> getEntriesKlasse() {

        List<Klasse> entries = klasseRepository.findAll();

        List<KlasseDto> result = entries.stream()
                .map(entity -> new KlasseDto(entity.getKlassenbezeichnung(), entity.getAnzSchueler(), entity.getKlassenraum()))
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/exams")
    public ResponseEntity<?> postEntry(@RequestBody ExamDto payload) {
        Exam entity = new Exam();
        entity.setStunde(payload.stunde());
        entity.setSubject(payload.subject());
        entity.setTopic(payload.topic());
        entity.setSontiges(payload.sonstiges());
    
        entity = examRepository.save(entity);
    
        URI location = UriComponentsBuilder.fromUriString("/exams/{id}")
            .build(entity.getId());
    
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<ExamDto> getSingle(@PathVariable String id) {
        return examRepository.findById(id)
        .map(entity -> new ExamDto(entity.getId(), entity.getClassname(), entity.getStunde(), entity.getSubject(), entity.getTopic(), entity.getSontiges()))
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
        entity.setStunde(payload.stunde());
        entity.setSubject(payload.subject());
        entity.setTopic(payload.topic());
        return entity;
        });
        if (examEntry.isPresent()) {
        Exam savedEntity = examRepository.save(examEntry.get());
        return ResponseEntity.ok(new ExamDto(savedEntity.getId(), savedEntity.getClassname(), savedEntity.getStunde(), savedEntity.getSubject(), savedEntity.getTopic(), savedEntity.getSontiges()));
        } else {
        return ResponseEntity.notFound().build();
        }
    }

}

