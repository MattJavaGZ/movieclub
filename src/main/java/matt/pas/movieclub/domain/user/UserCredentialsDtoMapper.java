package matt.pas.movieclub.domain.user;

import matt.pas.movieclub.domain.user.dto.UserAdministrationDto;
import matt.pas.movieclub.domain.user.dto.UserCredentialsDto;

public class UserCredentialsDtoMapper {

    public static UserCredentialsDto map(User user){
        return new UserCredentialsDto(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(UserRole::getName)
                        .toList()
        );
    }
    public static UserAdministrationDto mapToAdministrationUser(User user){
            return new UserAdministrationDto(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getNick(),
                    user.getRoles().stream()
                            .map(UserRole::getName)
                            .toList()
            );
    }
}
