package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.models.League;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.CompetitionService;
import pl.coderslab.climbingleague.service.LeagueService;
import pl.coderslab.climbingleague.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class LeagueAdministrationController {
    @Autowired
    private LeagueService leagueService;

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private UserService userService;

    @GetMapping("/leagues/edit/{id}")
    public String editLeague(@PathVariable Long id, Model model) {
        Optional<League> leagueOptional = leagueService.findById(id);
        if (!leagueOptional.isPresent()) {
            return "redirect:/leagues";
        }

        League league = leagueOptional.get();
        List<Competition> competitionsWithoutLeague = competitionService.findByLeagueIsNull();

        model.addAttribute("league", league);
        model.addAttribute("competitionsWithoutLeague", competitionsWithoutLeague);
        return "leagues-administration-edit";
    }

    @PostMapping("/leagues/update")
    public String updateLeague(@RequestParam Long id, @RequestParam String name, @RequestParam String description, @RequestParam String adminEmail) {
        Optional<League> leagueOptional = leagueService.findById(id);
        if (!leagueOptional.isPresent()) {
            return "redirect:/leagues";
        }

        League league = leagueOptional.get();
        league.setName(name);
        league.setDescription(description);

        User admin = userService.findUserByEmail(adminEmail);
        league.setCreatedBy(admin);

        leagueService.save(league);
        return "redirect:/leagues/edit/" + id;
    }

    @GetMapping("/leagues/removeCompetition/{competitionId}/{leagueId}")
    public String removeCompetitionFromLeague(@PathVariable Long competitionId, @PathVariable Long leagueId) {
        Optional<Competition> competitionOptional = competitionService.findById(competitionId);
        if (competitionOptional.isPresent()) {
            Competition competition = competitionOptional.get();
            competition.setLeague(null);
            competitionService.save(competition);
        }
        return "redirect:/leagues/edit/" + leagueId;
    }

    @GetMapping("/leagues/addCompetition/{competitionId}/{leagueId}")
    public String addCompetitionToLeague(@PathVariable Long competitionId, @PathVariable Long leagueId) {
        Optional<Competition> competitionOptional = competitionService.findById(competitionId);
        Optional<League> leagueOptional = leagueService.findById(leagueId);

        if (competitionOptional.isPresent() && leagueOptional.isPresent()) {
            Competition competition = competitionOptional.get();
            competition.setLeague(leagueOptional.get());
            competitionService.save(competition);
        }
        return "redirect:/leagues/edit/" + leagueId;
    }
    @PostMapping("/leagues/add")
    public ResponseEntity<String> addLeague(@RequestParam String name, @RequestParam String description, @AuthenticationPrincipal UserDetails currentUser) {
        if (leagueService.findIfLeagueExistsByName(name)) {
            return ResponseEntity.badRequest().body("Liga o takiej nazwie już istnieje");
        }

        League league = new League();
        league.setName(name);
        league.setDescription(description);

        User admin = userService.findUserByEmail(currentUser.getUsername());
        league.setCreatedBy(admin);

        leagueService.save(league);
        return ResponseEntity.ok("Liga została dodana");
    }
    @GetMapping("/leagues-administration/delete/{id}")
    public String deleteLeague(@PathVariable Long id) {
        leagueService.deleteById(id);
        return "redirect:/leagues-administration";
    }
}

