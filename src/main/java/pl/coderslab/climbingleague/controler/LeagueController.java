package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.climbingleague.models.League;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.CompetitionService;
import pl.coderslab.climbingleague.service.LeagueService;

import java.util.Optional;

@Controller
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private CompetitionService competitionService;


    @GetMapping
    public String getLeagues(Model model) {
        model.addAttribute("leagues", leagueService.findAll());
        model.addAttribute("league", new League());

        return "leagues";
    }

    @PostMapping()
    public String addLeague(League league){
        leagueService.save(league);
        return "redirect:/leagues";
    }


    @GetMapping("/details/{id}")
    public String getLeagueDetails(@PathVariable Long id, Model model){
        Optional<League> leagueOptional = leagueService.findById(id);
        if(leagueOptional.isPresent()){
            League league = leagueOptional.get();
            model.addAttribute("league", league);
            model.addAttribute("competitions", competitionService.findByLeagueId(id));
            return "leagues-details";
        }
        else {
            return "redirect:/leagues";
        }

    }

}
