package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.League;
import pl.coderslab.climbingleague.models.User;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
