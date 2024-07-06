package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.service.MessageService;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/")
    public String test(Model model){
        model.addAttribute("message", messageService.getMessage());
        return "index";
    }
}
