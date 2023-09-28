package de.destatis.klausurplaner.dataTransferObjects;

import lombok.Data;

/**
 * @author Marius
 * 
 * Datentransferobjekt für Login-Daten
 */
@Data
public class LoginDto {

    private String username;
    private String password;
}
