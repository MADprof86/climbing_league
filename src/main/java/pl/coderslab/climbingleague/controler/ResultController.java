package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.service.ResultService;

@Controller
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/results")
    public String listResults(Model model) {
        model.addAttribute("results", resultService.findAll());
        return "results";
    }
}
