package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Boulder;
import pl.coderslab.climbingleague.repositories.BoulderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoulderService {
    private static final Logger logger = LoggerFactory.getLogger(BoulderService.class);


    @Autowired
    private BoulderRepository boulderRepository;

    public List<Boulder> findAll() {
        logger.info("Fetching all boulders");
        return boulderRepository.findAll();
    }

    public Optional<Boulder> findById(Long id) {
        logger.info("Fetching boulder with id: {}", id);
        return boulderRepository.findById(id);
    }

    public Boulder save(Boulder boulder) {
        logger.info("Saving boulder: {}", boulder.getName());
        return boulderRepository.save(boulder);
    }

    public void deleteById(Long id) {
        logger.info("Deleting boulder with id: {}", id);
        boulderRepository.deleteById(id);
    }
}
