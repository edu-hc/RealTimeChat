package com.realtime.chat.repositories;

import com.realtime.chat.entities.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, Long> {
}
