package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.ScoresService;
import pl.coderslab.climbingleague.service.UserService;

import java.util.List;

@Controller
public class UserResultsController {
    @Autowired
    private ScoresService scoresService;
    @Autowired
    private UserService userService;

    @GetMapping("/results")
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
}
