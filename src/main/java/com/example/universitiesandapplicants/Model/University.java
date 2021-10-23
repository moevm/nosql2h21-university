package com.example.universitiesandapplicants.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "universities")
public class University {

    @Id
    private String id;

    private String name;

    private String city;

    private Contact contacts;

    public University(String name, String city, Contact contacts) {
        this.name = name;
        this.city = city;
        this.contacts = contacts;
    }
}
