package de.destatis.klausurplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam,String> {

    // SELECT 1 FROM exams WHERE classname = x
    //Exam findByName(String classname);
}

