package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.models.League;
import pl.coderslab.climbingleague.service.CompetitionService;
import pl.coderslab.climbingleague.service.LeagueService;

import java.util.List;

@Controller
public class ManageCompetitionsController {
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/leagues-administration")
    public String manageLeagues(Model model){
        List<League> leagues = leagueService.findAll();
        List<Competition> competitions = competitionService.findByLeagueIsNull();
        model.addAttribute("leagues",leagues);
        model.addAttribute("competitionsWithoutLeague", competitions);
        return "leagues-administration";
    }
}
