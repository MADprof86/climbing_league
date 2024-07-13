package pl.coderslab.climbingleague.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.service.ScoresService;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ScoresController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScoresService.class);

    @Autowired
    private ScoresService scoresService;

    @GetMapping("/competition/{id}")
    public String listScoresByCompetition(@PathVariable Long id, Model model) {
        LOGGER.info("looking for RESULTS");
        List<Scores> scores = scoresService.findByCompetitionId(id);

        model.addAttribute("results", scores);
        return "comp-results";
    }
}
