package com.realtime.chat.entities;

import jakarta.persistence.Id;
import lombok.*;
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
    private Long id;

    private String content;

    private User sender;

    private Chat chat;

    private LocalDateTime timestamp;
}
