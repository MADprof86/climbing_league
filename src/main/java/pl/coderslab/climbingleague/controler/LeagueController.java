package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.service.LeagueService;

@Controller
public class LeagueController {
    @Autowired
    private LeagueService leagueService;
    @GetMapping("/leagues")
    private String getLeagues(Model model){
        model.addAttribute("leagues", leagueService.findAll());
        return "leagues";
    }
}
