package matt.pas.movieclub.domain.user;

import matt.pas.movieclub.domain.user.dto.UserCredentialsDto;
import matt.pas.movieclub.domain.user.dto.UserRegisterDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final String DEFAULT_USER_ROLE = "USER";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserCredentialsDto> findUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .map(UserCredentialsDtoMapper::map);
    }

    public void userRegisterWithDefaultRole(UserRegisterDto user){
        final UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User userToSave = new User();
        userToSave.setEmail(user.getEmail());
        final String encodePassword = passwordEncoder.encode(user.getPassword());
        userToSave.setPassword(encodePassword);
        userToSave.getRoles().add(defaultRole);
        userRepository.save(userToSave);
    }
}
