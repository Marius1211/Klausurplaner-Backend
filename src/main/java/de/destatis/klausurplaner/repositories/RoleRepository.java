package de.destatis.klausurplaner.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    // SELECT 1 FROM exams WHERE classname = x
    
    Optional<Role> findByName(String name);

    
}
