package pl.coderslab.climbingleague.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.climbingleague.models.Result;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.service.ResultService;
import pl.coderslab.climbingleague.service.UserService;

import java.util.List;

@Controller
public class UserResultsController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private UserService userService;

    @GetMapping("/results")
    //Do modyfikacji po ustawieniu innego sposobu rankowania przy wprowadzaniu wyników
    private String getUserResults(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findUserByEmail(currentUser.getUsername());
        List<Result> results = resultService.findByUser(user);

        results.forEach(result -> {
            List<Result> competitionResults = resultService.findByCompetitionId(result.getCompetition().getId());
            result.setRank(competitionResults.stream().filter(r -> r.getUser().equals(user)).findFirst().get().getRank());
        });
        model.addAttribute("results",results);
        return "user-results";
    }
}
