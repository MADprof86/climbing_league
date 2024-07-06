package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Result;
import pl.coderslab.climbingleague.repositories.ResultRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    private static final Logger logger = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    private ResultRepository resultRepository;

    public List<Result> findAll() {
        logger.info("Fetching all results");
        return resultRepository.findAll();
    }

    public Optional<Result> findById(Long id) {
        logger.info("Fetching result with id: {}", id);
        return resultRepository.findById(id);
    }

    public Result save(Result result) {
        logger.info("Saving result");
        return resultRepository.save(result);
    }

    public void deleteById(Long id) {
        logger.info("Deleting result with id: {}", id);
        resultRepository.deleteById(id);
    }
}
