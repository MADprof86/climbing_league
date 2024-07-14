package pl.coderslab.climbingleague.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Błędny mail lub hasło");
        }
        if (logout != null) {
            model.addAttribute("message", "Zostałeś prawidłowo wylogowany.");
        }
        return "login";


    }
}
