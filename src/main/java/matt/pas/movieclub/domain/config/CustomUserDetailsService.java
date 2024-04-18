package matt.pas.movieclub.domain.config;

import matt.pas.movieclub.domain.user.UserService;
import matt.pas.movieclub.domain.user.dto.UserCredentialsDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findUserByEmail(username)
                .map(this::userToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Brak użytkownika o nazwie " + username));
    }

    private UserDetails userToUserDetails(UserCredentialsDto user){
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(String[]::new))
                .build();
    }
}
