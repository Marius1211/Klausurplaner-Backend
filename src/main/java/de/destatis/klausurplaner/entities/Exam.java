package de.destatis.klausurplaner.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table (name = "exams")
@NoArgsConstructor
@Getter
@Setter
public class Exam {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String classname;

    @Column
    private String subject;

    @Column
    private String topic;
}
