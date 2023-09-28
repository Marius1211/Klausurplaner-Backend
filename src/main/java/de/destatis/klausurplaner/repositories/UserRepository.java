package de.destatis.klausurplaner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.UserEntity;

/**
 * @author Marius
 * 
 * JPA Repository für vereinfachten Zugriff auf die  Datenbanktabelle für Benutzer
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    // SELECT 1 FROM exams WHERE classname = x
    
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
