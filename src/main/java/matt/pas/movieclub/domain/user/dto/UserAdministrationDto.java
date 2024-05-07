package matt.pas.movieclub.domain.user.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAdministrationDto {
    private Long id;
    private String email;
    private String password;
    private String nick;
    private List<String> roles = new ArrayList<>();

    public UserAdministrationDto(Long id, String email, String password, String nick, List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nick = nick;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
