package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        logger.info("Finding all users");
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        logger.info("Finding user with id: {}",id);
        return userRepository.findById(id);
    }

    public User save(User user){
        logger.info("Saving user with id: {}", user.getId());
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        logger.info("Deleting user with id {}", id);
    }


}
