package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.Permission;
import pl.coderslab.climbingleague.repositories.PermissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> findAll() {
        logger.info("Fetching all permissions");
        return permissionRepository.findAll();
    }

    public Optional<Permission> findById(Long id) {
        logger.info("Fetching permission with id: {}", id);
        return permissionRepository.findById(id);
    }

    public Permission save(Permission permission) {
        logger.info("Saving permission");
        return permissionRepository.save(permission);
    }

    public void deleteById(Long id) {
        logger.info("Deleting permission with id: {}", id);
        permissionRepository.deleteById(id);
    }
}
