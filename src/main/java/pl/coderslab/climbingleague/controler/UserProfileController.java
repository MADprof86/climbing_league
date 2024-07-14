package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.climbingleague.exceptions.EmailUsedException;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.UserService;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "user-profile";
    }

    @PostMapping("/profile/update")
    public String updateUserProfile(@AuthenticationPrincipal UserDetails currentUser,
                                    @RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam String email,
                                    @RequestParam(required = false) String password,
                                    @RequestParam(required = false) String confirmPassword,

                                    Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        if (password != null && !password.isEmpty()) {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("user", user);
                model.addAttribute("errorMessage", "Hasła nie są takie same.");
                return "user-profile";
            }
            user.setPassword(password);
        }
        try {
            userService.saveWithChangedEmail(user);
        }
        catch (EmailUsedException e){
            model.addAttribute("user",user);
            model.addAttribute("errorMessage", "There is an abbount with the same email");
            return "user-profile";
        }
        return "redirect:/profile";
    }
}
