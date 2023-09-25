import src\main\java\de\destatis\klausurplaner\repositories\UserRepository.java;
import src\main\java\de\destatis\klausurplaner\entities\UserEntity.java;
import org.springframework.security.core.userdetails.userdetails;
import org.springframework.security.core.userdetails.userdetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserVerification implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserVerification(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.FindByUsername(username).onElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
