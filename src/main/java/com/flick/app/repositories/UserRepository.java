package com.flick.app.repositories;

import com.flick.app.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findByUserEmailAndPassword(String userEmail, String password);
}
