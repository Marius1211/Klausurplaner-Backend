package de.destatis.klausurplaner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    // SELECT 1 FROM exams WHERE classname = x
    
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
