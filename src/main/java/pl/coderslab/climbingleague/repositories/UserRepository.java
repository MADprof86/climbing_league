package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
