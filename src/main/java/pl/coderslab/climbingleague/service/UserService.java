package pl.coderslab.climbingleague.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.climbingleague.exceptions.EmailUsedException;
import pl.coderslab.climbingleague.models.User;
import pl.coderslab.climbingleague.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        logger.info("Finding all users");
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        logger.info("Finding user with id: {}",id);
        return userRepository.findById(id);
    }

    public void save(User user) throws EmailUsedException {
        logger.info("Saving user with id: {}", user.getId());
        if(userRepository.findUserByEmail(user.getEmail()) != null){
            throw new EmailUsedException("There is an account with provided email");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        logger.info("Encoded password: {}", encodedPassword);
        user.setPassword(encodedPassword);
        user.setRole(User.Role.USER);
        userRepository.save(user);
    }

    public void deleteById(Long id){

        logger.info("Deleting user with id {}", id);
        userRepository.deleteById(id);
    }
    public User findUserByEmail(String email){
        logger.info("Finding user by email");
        return userRepository.findUserByEmail(email);
    }


}
