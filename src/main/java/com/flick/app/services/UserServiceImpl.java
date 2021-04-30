package com.flick.app.services;

import com.flick.app.models.Password;
import com.flick.app.models.User;
import com.flick.app.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.flick.app.exceptions.UserExceptions.*;

@Service
public class UserServiceImpl implements UserService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final String SECRET = "chandan@3706";
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, String> loginUser(String userEmail, String password) throws UserIdAndPasswordMismatch, UserNotFound {
        String s = null;
        Map<String, String> tokenMap = new HashMap<>();
        if (this.userRepository.existsById(userEmail)) {
            User user = this.userRepository.findByUserEmailAndPassword(userEmail, password);
            if (user.getUserEmail().equals(userEmail) && user.getPassword().equals(password)) {
                String token = generateToken(user.getFirstName());
                tokenMap.put("access_token", token);
                tokenMap.put("userId", userEmail);
            } else {
                throw new UserIdAndPasswordMismatch("UserId and password did not match");
            }
        } else {
            throw new UserNotFound("User does not exists");
        }
        return tokenMap;
    }

    @Override
    public Map<String, Boolean> registerUser(User user) throws UserAlreadyExists {
        Map<String, Boolean> map = new HashMap<>();
        if (!userRepository.existsById(user.getUserEmail())) {
            user.setUserActive(Boolean.TRUE);
            user.setUserAddedOn(LocalDate.now());
            userRepository.save(user);
            map.put("status", Boolean.TRUE);
        } else {
            throw new UserAlreadyExists("User does not exists");
        }
        return map;
    }

    @Override
    public Boolean changePassword(Password password) throws UserNotFound {
        boolean updateStatus = false;
        Optional<User> userOptional = userRepository.findById(password.getUserEmail());
        if (userOptional.isEmpty()) {
            User user = userRepository.findByUserEmailAndPassword(password.getUserEmail(), password.getCurrentPassword());
            if (user.getPassword().equals(password.getCurrentPassword())) {
                user.setPassword(password.getNewPassword());
                userRepository.save(user);
                updateStatus = true;
            }
        } else {
            throw new UserNotFound("User Not found for the email:- " + password.getUserEmail());
        }
        return updateStatus;
    }

    @Override
    public Boolean changeProfile(User user) {
        return null;
    }

    @Override
    public User getUserProfileByUserEmail(String userEmail) throws UserNotFound {
        User user;
        Optional<User> userOptional = userRepository.findById(userEmail);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            throw new UserNotFound("User Not found for the email:- " + userEmail);
        }
        return user;
    }

    private String generateToken(String firstName) {
        return Jwts.builder()
                .setSubject(firstName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }
}
