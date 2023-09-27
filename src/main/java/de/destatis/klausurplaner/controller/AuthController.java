package de.destatis.klausurplaner.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.destatis.klausurplaner.dataTransferObjects.RegisterDto;
import de.destatis.klausurplaner.entities.Role;
import de.destatis.klausurplaner.entities.UserEntity;
import de.destatis.klausurplaner.repositories.RoleRepository;
import de.destatis.klausurplaner.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
     * Neuen Benutzer Registrieren
     * POST: http://localhost:8080/api/auth/register
     *  {
     *      "role"      :   "$ROLE"  
     *      "username"  :   "$USERNAME"
     *      "password"  :   "$PASSWORD"
     *  }
     */
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        
        //Wenn Rolle nicht vorhanden FEHLER
        if(!roleRepository.existsByName(registerDto.getRole())) {
            return new ResponseEntity<String>("Rolle nicht vorhanden!", HttpStatus.BAD_REQUEST);
        }

        //Wenn Username bereits vorhanden FEHLER
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<String>("Benutzername ist bereits vorhanden!", HttpStatus.BAD_REQUEST);
        }


        //Erstelle neuen Benutzer
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //Finde ausgewählte Rolle
        Role roles = roleRepository.findByName(registerDto.getRole()).get();
        user.setRoles(Collections.singletonList(roles));

        //Speichere Benutzer
        userRepository.save(user);

        return new ResponseEntity<String>("Nutzer erfolgreich registriert", HttpStatus.OK);
    }
}
