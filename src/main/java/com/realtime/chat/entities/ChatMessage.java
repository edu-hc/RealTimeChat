package com.realtime.chat.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ChatMessage {

    @Id
    private String id;

    private String content;

    private Long senderId;

    private Long chatId;

    private LocalDateTime timestamp;

    public ChatMessage(String content, Long senderId, Long chatId) {
        this.content = content;
        this.senderId = senderId;
        this.chatId = chatId;
    }
}
