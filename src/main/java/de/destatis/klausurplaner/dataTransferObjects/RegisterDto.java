package de.destatis.klausurplaner.dataTransferObjects;

import lombok.Data;

@Data
public class RegisterDto {
    private String role;
    private String username;
    private String password;
}
