package pl.coderslab.climbingleague.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.climbingleague.models.Permission;
import pl.coderslab.climbingleague.models.User;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
