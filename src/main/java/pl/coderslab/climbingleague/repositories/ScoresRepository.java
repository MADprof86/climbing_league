package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.Scores;
import pl.coderslab.climbingleague.models.User;

import java.util.List;

public interface ScoresRepository extends JpaRepository<Scores, Long> {
    List<Scores> findAllByCompetitionId(Long competitionId);
    List<Scores> findAllByUser(User user);
    Scores findByUserAndCompetitionId(User user, Long competitionId);

}
