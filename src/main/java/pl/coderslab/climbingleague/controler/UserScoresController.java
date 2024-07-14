package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.climbingleague.models.BoulderResult;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.BoulderResultService;
import pl.coderslab.climbingleague.service.ScoresService;
import pl.coderslab.climbingleague.service.UserService;

import java.util.List;

@Controller
public class UserScoresController {
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private UserService userService;
    @Autowired
    private BoulderResultService boulderResultService;

    @GetMapping("/scores")
    //Do modyfikacji po ustawieniu innego sposobu rankowania przy wprowadzaniu wyników
    private String getUserResults(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername());
        List<Scores> results = scoresService.findByUser(user);

        results.forEach(result -> {
            List<Scores> competitionResults = scoresService.findByCompetitionId(result.getCompetition().getId());
            result.setCurrentRanking(competitionResults.stream().filter(r -> r.getUser().equals(user)).findFirst().get().getCurrentRanking());
        });
        model.addAttribute("results",results);
        return "user-results";
    }
    @GetMapping("/scores/details/{id}")
    public String getUserResultDetails(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername());
        Scores result = scoresService.findById(id).orElse(null);

        if (result == null || !result.getUser().equals(user)) {
            return "redirect:/results";
        }

        List<BoulderResult> boulderResults = boulderResultService.findByScores(result);

        model.addAttribute("result", result);
        model.addAttribute("boulderResults", boulderResults);
        return "user-results-details";
    }
//    @GetMapping("/results/details/{competitionId}")
//    public String getUserResultDetails(@PathVariable Long competitionId, @AuthenticationPrincipal UserDetails currentUser, Model model) {
//        User user = userService.findUserByEmail(currentUser.getUsername());
//        Scores result = scoresService.findByUserAndCompetitionId(user, competitionId);
//
//        if (result == null) {
//            return "redirect:/results";
//        }
//
//        List<BoulderResult> boulderResults = boulderResultService.findByScores(result);
//
//        model.addAttribute("result", result);
//        model.addAttribute("boulderResults", boulderResults);
//        return "user-results-details";
//    }
}
