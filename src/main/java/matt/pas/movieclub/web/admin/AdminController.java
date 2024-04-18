package matt.pas.movieclub.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    public final static String NOTIFICATION_ATTRIBUTE = "notification";

    @GetMapping("/admin")
    String adminPage() {
        return "admin/admin";
    }
}
