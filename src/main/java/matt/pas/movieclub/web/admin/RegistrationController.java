package matt.pas.movieclub.web.admin;

import matt.pas.movieclub.domain.user.UserService;
import matt.pas.movieclub.domain.user.dto.UserRegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rejestracja")
    String registrationForm(Model model){
        final UserRegisterDto userRegistration  = new UserRegisterDto();
        model.addAttribute("user", userRegistration );
        return "registration-form";
    }
    @PostMapping("/rejestracja")
    String registration(UserRegisterDto user){
        userService.userRegisterWithDefaultRole(user);
        return "redirect:/";
    }
}
