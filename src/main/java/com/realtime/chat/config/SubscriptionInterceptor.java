package com.realtime.chat.config;

import com.realtime.chat.repositories.ChatRepository;
import com.realtime.chat.repositories.UserRepository;
import com.realtime.chat.services.ChatService;
import com.realtime.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionInterceptor implements ChannelInterceptor {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public SubscriptionInterceptor(ChatRepository chatRepository, UserRepository userRepository, UserService userService, ChatService chatService) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // Apenas interceptar mensagens de subscrição (SUBSCRIBE)
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            // Obter o destino (ex: /chat/123)
            String destination = headerAccessor.getDestination();

            // Extrair o chatId do destino
            if (destination != null && destination.startsWith("/chat/")) {
                String chatId = destination.substring("/chat/".length());

                // Obter o userId da sessão
                String userId = (String) headerAccessor.getSessionAttributes().get("userId");

                if (userId != null && !userId.isEmpty()) {

                    try {
                        userService.addChat(userId, chatId);
                        chatService.addUser(userId, chatId);
                    } catch (Exception e) {
                        System.err.println("Falha ao inscrever usuário no chat: " + e.getMessage());
                    }
                }
            }
        }

        return message;
    }
}
