package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.repositories.ScoresRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ScoresService {
    private static final Logger logger = LoggerFactory.getLogger(ScoresService.class);

    @Autowired
    private ScoresRepository scoresRepository;

    public List<Scores> findAll() {
        logger.info("Fetching all results");
        return scoresRepository.findAll();
    }

    public Optional<Scores> findById(Long id) {
        logger.info("Fetching result with id: {}", id);
        return scoresRepository.findById(id);
    }

    public Scores save(Scores result) {
        logger.info("Saving result");
        return scoresRepository.save(result);
    }

    public void deleteById(Long id) {
        logger.info("Deleting result with id: {}", id);
        scoresRepository.deleteById(id);
    }
    public List<Scores> findByCompetitionId(Long competitionId){
        //Ustawiamy od razu sortowanie - potem musimy dodać jeszcze podział na kategorie
        List<Scores> scores = scoresRepository.findAllByCompetitionId(competitionId);
        scores.sort(Comparator.comparingDouble(Scores::getTotalPoints).reversed());
        for (int i = 0; i < scores.size() ; i++) {
            scores.get(i).setCurrentRanking(i+1);

        }
        return scores;

    }

    public List<Scores> findByUser(User user) {
        return  scoresRepository.findAllByUser(user);
    }

    public Scores findByUserAndCompetitionId(User user, Long competitionId) {
        return  scoresRepository.findByUserAndCompetitionId(user,competitionId);
    }
}
