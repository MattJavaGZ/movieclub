package matt.pas.movieclub.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import matt.pas.movieclub.domain.user.validate.UserEmailConstraint;
import matt.pas.movieclub.domain.user.validate.UserNickConstraint;

public class UserRegisterDto {
    @NotEmpty
    @Email
    @UserEmailConstraint
    private String email;
    @NotEmpty
    @Size(min = 8, message = "Hasło musi składać się z min {min} znaków")
    private String password;
    @NotEmpty
    @UserNickConstraint
    private String nick;

    public UserRegisterDto(String email, String password, String nick) {
        this.email = email;
        this.password = password;
        this.nick = nick;
    }

    public UserRegisterDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
