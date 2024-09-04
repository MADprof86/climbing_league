package pl.coderslab.climbingleague.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.climbingleague.models.Boulder;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.service.BoulderService;
import pl.coderslab.climbingleague.service.CompetitionService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private BoulderService boulderService;


    @GetMapping()
    public String listCompetitions(Model model) {
        model.addAttribute("competitions", competitionService.findAll());
        return "competitions";
    }
    @GetMapping("/details/{id}")
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
        List<Boulder.DifficultyLevel> difficulties = Arrays.asList(Boulder.DifficultyLevel.values());
        model.addAttribute("newBoulder", new Boulder());
        model.addAttribute("competition", competition);
        model.addAttribute("competitionId", competition.getId());
        model.addAttribute("competitionTypes", competitionTypes);
        model.addAttribute("scoreSystems", scoreSystems);
        model.addAttribute("boulders", competition.getBoulders());
        model.addAttribute("difficulties", difficulties);

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

//    @PostMapping("/update")
//    public String updateCompetition(@ModelAttribute("competition") @Valid Competition competition, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            List<Competition.CompetitionType> competitionTypes = Arrays.asList(Competition.CompetitionType.values());
//            List<Competition.ScoreSystem> scoreSystems = Arrays.asList(Competition.ScoreSystem.values());
//
//            model.addAttribute("competitionTypes", competitionTypes);
//            model.addAttribute("scoreSystems", scoreSystems);
//            model.addAttribute("competition", competition);
//            model.addAttribute("errorMessage", "Błąd podczas zapisywania danych. Sprawdź wprowadzone informacje.");
//            return "competitions-edit";
//        }
//
//        competitionService.save(competition);
//        model.addAttribute("successMessage", "Dane zawodów zostały pomyślnie zapisane.");
//        return "redirect:/competitions/edit/" + competition.getId();
//    }
    @PostMapping("/update")
    public String updateCompetition(@ModelAttribute("competition") @Valid Competition competition, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("competitionTypes", Competition.CompetitionType.values());
            model.addAttribute("scoreSystems", Competition.ScoreSystem.values());
            model.addAttribute("difficulties", Boulder.DifficultyLevel.values());
            return "competitions-edit";
        }

        competitionService.save(competition);
        model.addAttribute("successMessage", "Dane zawodów zostały pomyślnie zapisane.");
        return "redirect:/competitions/edit/" + competition.getId();
    }
    @PostMapping("/boulders/add")
    public String addBoulder(@RequestParam("competition.id") Long competitionId,
                             @ModelAttribute("boulder") @Valid Boulder boulder,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "redirect:/competitions/edit/" + boulder.getCompetition().getId();
        }
        Competition competition = competitionService.findById(competitionId).orElse(null);
        if(competition == null){
            model.addAttribute("errorMessage","Błąd podczas zapisywania bouleru. Brak zawodów.");
            return "redirect:/competitions";
        }
        boulder.setCompetition(competition);
        boulderService.save(boulder);
        return "redirect:/competitions/edit/" + boulder.getCompetition().getId();
    }

    @PostMapping("/boulders/delete/{boulderId}")
    public String deleteBoulder(@PathVariable Long boulderId) {
        Optional<Boulder> boulder = boulderService.findById(boulderId);
        if (boulder.isPresent()) {
            boulderService.delete(boulder.get());
        }
        return "redirect:/competitions/edit/" + boulder.get().getCompetition().getId();
    }
}
