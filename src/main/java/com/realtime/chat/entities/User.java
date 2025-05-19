package com.realtime.chat.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private Set<String> chats = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
