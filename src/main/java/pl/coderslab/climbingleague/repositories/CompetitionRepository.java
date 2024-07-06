package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.Competition;
import pl.coderslab.climbingleague.models.User;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
