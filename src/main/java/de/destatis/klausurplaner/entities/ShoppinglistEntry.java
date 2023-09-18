package de.destatis.klausurplaner.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ShoppinglistEntry {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String id;

    @Column
    private String title;

    @Column
    private Integer amount;

    @Column
    private String category;
}
