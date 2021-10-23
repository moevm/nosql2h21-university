package com.example.universitiesandapplicants.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Contact {

    private String email;

    private String phone;

    public Contact(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
