package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.Boulder;
import pl.coderslab.climbingleague.models.BoulderResult;
import pl.coderslab.climbingleague.models.Scores;

import java.util.List;

public interface BoulderResultRepository extends JpaRepository<BoulderResult,Long> {
    List<BoulderResult> findAllByScores(Scores scores);

    List<BoulderResult> findByBoulder(Boulder boulder);


}
