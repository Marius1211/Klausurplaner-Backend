package de.destatis.klausurplaner.repositories;

import java.util.Optional;

import de.destatis.klausurplaner.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, String>
{

    Optional<Calendar> findByKlausurArt(String klausurArt);
    Boolean existsByKlausurArt(String klausurArt);

}
