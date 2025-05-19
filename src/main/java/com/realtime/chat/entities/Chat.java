package com.realtime.chat.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "chats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Chat {

    @Id
    private String id;

    private String name;

    private Set<String> members = new HashSet<>();

    private List<ChatMessage> messages = new ArrayList<>();

    public Chat(String name) {
        this.name = name;
    }
}
