package pl.coderslab.climbingleague.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Boulder;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.repositories.BoulderRepository;
import pl.coderslab.climbingleague.repositories.CompetitionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService {
    private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private BoulderRepository boulderRepository;

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
    public List<Competition> findByLeagueIsNull(){
        logger.info("Looking for repository by league null");
        return competitionRepository.findAllByLeagueIsNull();
    }
    @Transactional
    public Competition updateCompetitionAndBoulders(Competition competition) {
        Competition existingCompetition = competitionRepository.findById(competition.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid competition Id:" + competition.getId()));


        for (Boulder boulder : competition.getBoulders()) {
            if (boulder.getId() != null) {

                Boulder existingBoulder = boulderRepository.findById(boulder.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid boulder Id:" + boulder.getId()));

                existingBoulder.setName(boulder.getName());
                existingBoulder.setPointsForTop(boulder.getPointsForTop());
                existingBoulder.setPointsForZone(boulder.getPointsForZone());
                existingBoulder.setDifficulty(boulder.getDifficulty());

                boulderRepository.save(existingBoulder);
            } else {

                boulder.setCompetition(existingCompetition);
                boulderRepository.save(boulder);
            }
        }

        //sprawdzić czy na pewno nie wywala błędu
        existingCompetition.getBoulders().removeIf(boulder -> !competition.getBoulders().contains(boulder));

        return competitionRepository.save(existingCompetition);
    }

}


