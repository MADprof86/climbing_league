package pl.coderslab.climbingleague.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.service.CompetitionService;
import pl.coderslab.climbingleague.service.ScoresService;
import pl.coderslab.climbingleague.service.UserService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/scores")
public class ScoresController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoresService.class);
    @Autowired
    private ScoresService scoresService;

    @GetMapping("/competition/{id}")
    public String listScoresByCompetition(@PathVariable Long id, Model model) {
        LOGGER.info("looking for RESULTS");
        List<Scores> scores = scoresService.findByCompetitionId(id);

        model.addAttribute("results", scores);
        return "competitions-results";
    }

}
