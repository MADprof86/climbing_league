package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.BoulderResult;

public interface BoulderResultRepository extends JpaRepository<BoulderResult,Long> {
}
