package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.climbingleague.exceptions.EmailUsedException;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String getRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "registration";

    }
    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("user") @Valid User user,
                                  BindingResult result,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            result.rejectValue("password", "error.user", "Hasła nie są zgodne.");
            return "registration";
        }

        try {

            userService.save(user);
        } catch (EmailUsedException e) {
            model.addAttribute("emailExists", true);
            return "registration";
        }

        return "redirect:/login";
    }
}

