package pl.coderslab.climbingleague.controler;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.climbingleague.service.MessageService;
import org.slf4j.Logger;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

//    @GetMapping("/")
//    public String test(Model model){
//        logger.info("Redirecting message test");
//        model.addAttribute("message", messageService.getMessage());
//        return "index";
//    }
    @GetMapping("/")
    public String home(@RequestParam(value = "login_success", required = false) String loginSuccess, Model model) {
        if (loginSuccess != null) {
            model.addAttribute("message", "Zalogowałeś się poprawnie");
        }
        return "index";
    }
}
