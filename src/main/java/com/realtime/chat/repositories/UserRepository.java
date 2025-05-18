package com.realtime.chat.repositories;

import com.realtime.chat.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
