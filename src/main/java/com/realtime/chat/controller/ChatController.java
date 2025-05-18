package com.realtime.chat.controller;

import com.realtime.chat.entities.Chat;
import com.realtime.chat.entities.ChatMessage;
import com.realtime.chat.repositories.ChatRepository;
import com.realtime.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Controller
public class ChatController {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/message/{chatId}")
    @SendTo("/chat/{chatId}")
    public ChatMessage sendMessage(@DestinationVariable Long chatId, @Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        // Obter o ID do usuário da sessão WebSocket
        Long userId = (Long) headerAccessor.getSessionAttributes().get("userId");

        // Buscar o chat no MongoDB
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado"));

        // Configurar a mensagem
        message.setId(UUID.randomUUID().toString());
        message.setSenderId(userId);
        message.setTimestamp(LocalDateTime.now());

        // Salvar a mensagem (opção 1: dentro do documento chat)
        chat.getMessages().add(message);
        chatRepository.save(chat);

        // Retornar a mensagem para broadcast
        return message;

    }
}
