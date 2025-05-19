package com.realtime.chat.controller;

import com.realtime.chat.dtos.ChatDTO;
import com.realtime.chat.entities.Chat;
import com.realtime.chat.repositories.ChatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create-chat")
public class CreateChatController {

    private final ChatRepository chatRepository;

    public CreateChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @PostMapping
    public ResponseEntity createChat(@RequestBody ChatDTO chatDTO) {

        Chat newChat = new Chat(chatDTO.name());

        chatRepository.save(newChat);

        return ResponseEntity.ok(newChat);
    }
}
