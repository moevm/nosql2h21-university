package com.example.universitiesandapplicants.model.respose;

import com.example.universitiesandapplicants.entity.User;
import lombok.Data;

@Data
public class JwtResponseModel {
    private String id;
    private String email;
    private String role;

    public JwtResponseModel(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
