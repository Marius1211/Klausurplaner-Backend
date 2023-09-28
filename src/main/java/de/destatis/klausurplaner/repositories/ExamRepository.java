package de.destatis.klausurplaner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.Exam;
import java.util.List;

/**
 * @author Marius
 * 
 * JPA Repository für vereinfachten Zugriff auf die  Datenbanktabelle für Klausuren
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {
    
    // SELECT 1 FROM exams WHERE classname = x
    Optional<Exam> findByClassname(String classname);
    Boolean existsByClassname(String classname);
    
}

