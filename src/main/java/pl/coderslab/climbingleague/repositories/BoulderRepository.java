package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.Boulder;
import pl.coderslab.climbingleague.models.User;

public interface BoulderRepository extends JpaRepository<Boulder, Long> {
}
