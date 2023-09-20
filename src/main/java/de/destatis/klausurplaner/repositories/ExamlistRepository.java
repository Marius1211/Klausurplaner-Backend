package de.destatis.klausurplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.ExamlistEntry;

@Repository
public interface ExamlistRepository extends JpaRepository<ExamlistEntry,Long> {
    
}

