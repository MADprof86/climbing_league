package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Boulder;
import pl.coderslab.climbingleague.models.BoulderResult;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.repositories.BoulderResultRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoulderResultService {
    private static final Logger logger = LoggerFactory.getLogger(BoulderResultService.class);

    @Autowired
    private BoulderResultRepository boulderResultRepository;

    public List<BoulderResult> findAll() {
        logger.info("Fetching all boulder results");
        return boulderResultRepository.findAll();
    }

    public Optional<BoulderResult> findById(Long id) {
        logger.info("Fetching boulder result with id: {}", id);
        return boulderResultRepository.findById(id);
    }

    public BoulderResult save(BoulderResult boulderResult) {
        logger.info("Saving boulder result");
        return boulderResultRepository.save(boulderResult);
    }

    public void deleteById(Long id) {
        logger.info("Deleting boulder result with id: {}", id);
        boulderResultRepository.deleteById(id);
    }

    public List<BoulderResult> findByScores(Scores result) {
        logger.info("Looking for scores ");
        return boulderResultRepository.findAllByScores(result);
    }

    public List<BoulderResult> findByBoulder(Boulder boulder) {
        logger.info("Looking for bouders in results  by id{} ",boulder.getId());
        return boulderResultRepository.findByBoulder(boulder);
    }
}
