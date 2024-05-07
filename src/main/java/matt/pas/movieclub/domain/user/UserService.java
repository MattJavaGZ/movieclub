package matt.pas.movieclub.domain.user;

import com.sun.net.httpserver.HttpsServer;
import jakarta.transaction.Transactional;
import matt.pas.movieclub.domain.user.dto.UserAdministrationDto;
import matt.pas.movieclub.domain.user.dto.UserCredentialsDto;
import matt.pas.movieclub.domain.user.dto.UserRegisterDto;
import matt.pas.movieclub.email.EmailService;
import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private static final String DEFAULT_USER_ROLE = "USER";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public Optional<UserCredentialsDto> findActivUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCaseAndActivTrue(email)
                .map(UserCredentialsDtoMapper::map);
    }
    public Optional<User> findUserById(long id){
        return userRepository.findById(id);
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

    public void userRegisterWithDefaultRole(UserRegisterDto user) throws EmailException {
        final UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();
        User userToSave = new User();
        userToSave.setNick(user.getNick());
        userToSave.setEmail(user.getEmail());
        final String encodePassword = passwordEncoder.encode(user.getPassword());
        userToSave.setPassword(encodePassword);
        userToSave.getRoles().add(defaultRole);
        userToSave.setActiv(false);
        userToSave.setActivKey(genetareActivKey());
        final User savedUser = userRepository.save(userToSave);
        emailService.sendActivEmail(savedUser);

    }
    private String genetareActivKey(){
        return UUID.randomUUID().toString();
    }
    public List<UserAdministrationDto> findAllUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserCredentialsDtoMapper::mapToAdministrationUser)
                .toList();
    }
    @Transactional
    public boolean chechActivationAndActiv(long id, String activKey){
        final User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (user.getActivKey().equals(activKey)) {
            user.setActiv(true);
            return true;
        }
        return false;
    }
}
