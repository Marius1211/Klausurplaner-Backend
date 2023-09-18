package de.destatis.klausurplaner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.destatis.klausurplaner.entities.ShoppinglistEntry;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppinglistEntry,Long> {
    
}

