package com.flick.app.repositories;

import com.flick.app.models.UserMovie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<UserMovie, String> {
}
