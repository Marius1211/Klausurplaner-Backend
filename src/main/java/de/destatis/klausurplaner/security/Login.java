import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.destatis.klausurplaner.entities.UserEntity;


@RestController
@RequestMapping("/kp")
public class Login {

    private UserDetails user;

    @PostMapping("/login")
    public ResponseEntity<String> handlePostRequest(@RequestBody String requestData) {
        
        UserEntity temp= objectMapper.readValue(requestData);
        findUser(temp.getUsername());

        String response = "";

        if(verify(temp.getPassword())) {
            response = "Richtiges Passwort";
        } else {
            response = "Falsches Passwort";
        }

        return ResponseEntity.ok(response);
    }

    public void findUser(String username) {
        CustomUserDetailsService verification = new CustomUserDetailsService();

        this.user = verification.loadUserByUsername(username);
    }

    public boolean verify(String password) {
        if(new SecurityConfig().passwordEncoder().matches(password, user.getPassword)) {
            return true;
        } else {
            return false;
        }
    }
}