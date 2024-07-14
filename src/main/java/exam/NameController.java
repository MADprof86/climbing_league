package exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NameController {
    @GetMapping("/name")
    public String changeToUpperCase(@RequestParam String name){
        return name.toUpperCase();
    }


}
