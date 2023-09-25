package de.destatis.klausurplaner.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    
    @ManyToMany
    @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName="id"))
    private List<Role> roles = new ArrayList<>();
            

}
