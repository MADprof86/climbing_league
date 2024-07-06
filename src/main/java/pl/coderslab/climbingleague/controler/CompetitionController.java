package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.service.CompetitionService;

@Controller
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/competitions")
    public String listCompetitions(Model model) {
        model.addAttribute("competitions", competitionService.findAll());
        return "competitions";
    }
}
