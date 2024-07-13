package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.League;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.repositories.LeagueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private static final Logger logger = LoggerFactory.getLogger(LeagueService.class);

    @Autowired
    private LeagueRepository leagueRepository;

    public List<League> findAll() {
        logger.info("Fetching all leagues");
        return leagueRepository.findAll();
    }

    public Optional<League> findById(Long id) {
        logger.info("Fetching league with id: {}", id);
        return leagueRepository.findById(id);
    }

    public League save(League league) {
        logger.info("Saving league: {}", league.getName());
        return leagueRepository.save(league);
    }

    public void deleteById(Long id) {
        logger.info("Deleting league with id: {}", id);
        leagueRepository.deleteById(id);
    }


    public boolean findIfLeagueExistsByName(String name) {
        logger.info("Searching for league by name :{}",name);
        return leagueRepository.existsLeagueByName(name);
    }
}
