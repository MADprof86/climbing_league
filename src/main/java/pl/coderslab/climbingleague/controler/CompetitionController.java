package pl.coderslab.climbingleague.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.service.CompetitionService;

import java.util.Arrays;
import java.util.List;
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
            return "competitions-results";
        }
        return "redirect:/competitions";
    }
    @GetMapping("/edit/{id}")
    public String getEditCompetitionForm(@PathVariable Long id, Model model) {
        Competition competition = competitionService.findById(id).orElse(null);
        if (competition == null) {
            return "redirect:/competitions-results-edit"; // do ustawienia widok
        }
        List<Competition.CompetitionType> competitionTypes = getCompetitionTypes();
        List<Competition.ScoreSystem> scoreSystems = getScoreSystems();

        model.addAttribute("competition", competition);
        model.addAttribute("competitionTypes", competitionTypes);
        model.addAttribute("scoreSystems", scoreSystems);
        return "competitions-edit";
    }

    private static List<Competition.ScoreSystem> getScoreSystems() {
        List<Competition.ScoreSystem> scoreSystems = Arrays.asList(Competition.ScoreSystem.values());
        return scoreSystems;
    }

    private static List<Competition.CompetitionType> getCompetitionTypes() {
        List<Competition.CompetitionType> competitionTypes = Arrays.asList(Competition.CompetitionType.values());
        return competitionTypes;
    }

    @PostMapping("/update")
    public String updateCompetition(@ModelAttribute("competition") @Valid Competition competition, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Competition.CompetitionType> competitionTypes = Arrays.asList(Competition.CompetitionType.values());
            List<Competition.ScoreSystem> scoreSystems = Arrays.asList(Competition.ScoreSystem.values());

            model.addAttribute("competitionTypes", competitionTypes);
            model.addAttribute("scoreSystems", scoreSystems);
            model.addAttribute("competition", competition);
            model.addAttribute("errorMessage", "Błąd podczas zapisywania danych. Sprawdź wprowadzone informacje.");
            return "competitions-edit";
        }

        competitionService.save(competition);
        model.addAttribute("successMessage", "Dane zawodów zostały pomyślnie zapisane.");
        return "redirect:/competitions/edit/" + competition.getId();
    }
}
