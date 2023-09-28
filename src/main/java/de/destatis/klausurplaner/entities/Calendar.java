package de.destatis.klausurplaner.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Patrick
 * 
 * Datenbanktabelle für Kalendereinträge
 */
@Data
@Entity
@Table (name = "calendar")
@NoArgsConstructor
@Getter
@Setter
public class Calendar
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String schulstunde;

    @Column
    private String tag;

    @Column
    private String klausurArt;

}
