package de.destatis.klausurplaner.dataTransferObjects;

import lombok.Data;

/**
 * @author Marius
 * 
 * Datentransferobjekt für Registrierungsdaten
 */
@Data
public class RegisterDto {
    private String role;
    private String username;
    private String password;
}
