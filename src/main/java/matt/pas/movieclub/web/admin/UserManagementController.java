package matt.pas.movieclub.web.admin;

import matt.pas.movieclub.domain.user.UserService;
import matt.pas.movieclub.domain.user.dto.UserAdministrationDto;
import matt.pas.movieclub.domain.user.dto.UserCredentialsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserManagementController {

    private UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/lista-uzytkownikow")
    String userList(Model model) {
        final List<UserAdministrationDto> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "admin/admin-users-list";
    }

    @GetMapping("/admin/lista-uzytkownikow/edycja-email/{id}")
    String editEmailForm(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("header", "Zmień email");
        model.addAttribute("type", "email");
        return "admin/edit-user-form";
    }

    @PostMapping("/admin/lista-uzytkownikow/edycja-email/{id}")
    String editEmail(@PathVariable long id, @RequestParam String email) {
        userService.editEmail(id, email);
        return "redirect:/admin/lista-uzytkownikow";
    }

    @GetMapping("/admin/lista-uzytkownikow/edycja-password/{id}")
        String editPassForm(@PathVariable long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("header", "Zmień hasło");
        model.addAttribute("type", "password");
        return "admin/edit-user-form";

    }
    @PostMapping("/admin/lista-uzytkownikow/edycja-password/{id}")
    String editPass(@PathVariable long id, @RequestParam String password) {
        userService.editPass(id, password);
        return "redirect:/admin/lista-uzytkownikow";
    }
    @GetMapping("/admin/lista-uzytkownikow/zablokuj/{id}")
    String blockUser(@PathVariable long id) {
        userService.blockUser(id);
        return "redirect:/admin/lista-uzytkownikow";
    }

    @GetMapping("/admin/lista-uzytkownikow/odblokuj/{id}")
    String unlockUser(@PathVariable long id) {
        userService.unlockUser(id);
        return "redirect:/admin/lista-uzytkownikow";
    }
    @GetMapping("/admin/lista-uzytkownikow/usun/{id}")
    String deletrUser(@PathVariable long id){
        userService.deleteUser(id);
        return "redirect:/admin/lista-uzytkownikow";
    }

}
