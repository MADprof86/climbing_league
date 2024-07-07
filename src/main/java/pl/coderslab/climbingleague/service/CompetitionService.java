package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.repositories.CompetitionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService {
    private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;

    public List<Competition> findAll() {
        logger.info("Fetching all competitions");
        return competitionRepository.findAll();
    }

    public Optional<Competition> findById(Long id) {
        logger.info("Fetching competition with id: {}", id);
        return competitionRepository.findById(id);
    }

    public Competition save(Competition competition) {
        logger.info("Saving competition: {}", competition.getName());
        return competitionRepository.save(competition);
    }

    public void deleteById(Long id) {
        logger.info("Deleting competition with id: {}", id);
        competitionRepository.deleteById(id);
    }

    public List<Competition> findByLeagueId(Long id) {
        logger.info("Looking for competitions by league id {}", id);
        return competitionRepository.findAll().stream().filter(competition -> competition.getId().equals(id))
                .collect(Collectors.toList());
    }
}
