package de.destatis.klausurplaner.repositories;

import de.destatis.klausurplaner.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.Klasse;

import java.util.Optional;

public interface KlasseRepository extends JpaRepository<Klasse, String>
{

    Optional<Klasse> findByKlassenbezeichnung(String klassenbezeichnung);
    Boolean existsByKlassenbezeichnung(String klassenbezeichnung);

}
