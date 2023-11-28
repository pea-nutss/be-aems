package com.aaronbujatin.beaems.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {

    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String role;


}
