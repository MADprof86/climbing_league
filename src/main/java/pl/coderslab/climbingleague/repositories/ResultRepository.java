package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.Result;
import pl.coderslab.climbingleague.models.User;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
