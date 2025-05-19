package com.realtime.chat.services;

import com.realtime.chat.entities.Chat;
import com.realtime.chat.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void addUser(String userId, String chatId) {

        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        chat.getMembers().add(userId);
        chatRepository.save(chat);
    }
}
