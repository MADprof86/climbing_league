package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.service.CompetitionService;

import java.util.Optional;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @GetMapping()
    public String listCompetitions(Model model) {
        model.addAttribute("competitions", competitionService.findAll());
        return "competitions";
    }
    @GetMapping("details/{id}")
    public String getCompetitionDetails(@PathVariable Long id, Model model){
        Optional<Competition> competition = competitionService.findById(id);
        if(competition.isPresent()) {
            model.addAttribute("results", competition);
            return "comp-results";
        }
        return "redirect:/competitions";
    }
}
