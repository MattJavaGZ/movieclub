package matt.pas.movieclub.domain.user;

import com.sun.net.httpserver.HttpsServer;
import jakarta.transaction.Transactional;
import matt.pas.movieclub.domain.user.dto.UserAdministrationDto;
import matt.pas.movieclub.domain.user.dto.UserCredentialsDto;
import matt.pas.movieclub.domain.user.dto.UserRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
    @Transactional
    public void editEmail(long id, String newEmail){
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setEmail(newEmail);
    }
    @Transactional
    public void editPass(long id, String newPass) {
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final String encodePass = passwordEncoder.encode(newPass);
        user.setPassword(encodePass);

    }
    @Transactional
    public void blockUser(long id){
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final UserRole blockRole = userRoleRepository.findByName("BLOCK").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.getRoles().clear();
        user.getRoles().add(blockRole);
    }
    @Transactional
    public void unlockUser(long id){
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final UserRole userRole = userRoleRepository.findByName("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.getRoles().clear();
        user.getRoles().add(userRole);
    }
    public void deleteUser(long id){
        userRepository.deleteById(id);
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
    public List<UserAdministrationDto> findAllUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserCredentialsDtoMapper::mapToAdministrationUser)
                .toList();
    }
}
