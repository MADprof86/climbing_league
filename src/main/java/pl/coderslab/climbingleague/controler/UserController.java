package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String listUsers(Model model){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("user", new User());
        return "users";
    };
    @GetMapping("/deleteCredentials/{id}")
    public String anonymizeUser(@PathVariable Long id) {
        userService.deleteLoginCredentials(id);
        return "redirect:/users";
    }



    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return "redirect:/users";
    }



}
