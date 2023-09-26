package de.destatis.klausurplaner.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table (name = "klassen")
@NoArgsConstructor
@Getter
@Setter
public class Klasse
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String klassenbezeichnung;

    @Column
    private int anzSchueler;

    @Column
    private String klassenraum;

}
