package pl.coderslab.climbingleague.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.climbingleague.models.Result;
import pl.coderslab.climbingleague.service.ResultService;

import java.util.List;

@Controller
@RequestMapping("/results")
public class ResultController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    private ResultService resultService;

    @GetMapping("/competition/{id}")
    public String listResultsByCompetition(@PathVariable Long id, Model model) {
        LOGGER.info("looking for RESULTS");
        List<Result> results = resultService.findByCompetitionId(id);

        model.addAttribute("results", results);
        return "results";
    }
}
