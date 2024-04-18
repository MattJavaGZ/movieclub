package matt.pas.movieclub.domain.user.dto;

public class UserRegisterDto {

    private String email;
    private String password;

    public UserRegisterDto(String email, String password) {
        this.email = email;
        this.password = password;
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
}
