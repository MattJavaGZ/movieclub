package matt.pas.movieclub.web.admin;

import jakarta.validation.Valid;
import matt.pas.movieclub.domain.user.User;
import matt.pas.movieclub.domain.user.UserService;
import matt.pas.movieclub.domain.user.dto.UserRegisterDto;
import org.apache.commons.mail.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    String registration(@Valid @ModelAttribute("user") UserRegisterDto user, BindingResult bindingResult) throws EmailException {
        if (bindingResult.hasErrors()){
            return "registration-form";
        }
        else {
            userService.userRegisterWithDefaultRole(user);
        }
        return "activation";
    }
    @GetMapping("/aktywacja/{id}")
    String activation (@PathVariable long id, @RequestParam String activKey){
        final boolean isActiv = userService.chechActivationAndActiv(id, activKey);
        if (!isActiv) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else return "redirect:/login";

    }
}
