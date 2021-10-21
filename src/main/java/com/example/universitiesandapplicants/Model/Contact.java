package com.example.universitiesandapplicants.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "contact")
public class Contact {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String phone;

    public Contact(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
