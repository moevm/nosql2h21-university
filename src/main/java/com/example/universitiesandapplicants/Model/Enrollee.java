package com.example.universitiesandapplicants.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "enrollees")
public class Enrollee {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String password;

    private Date DOB;

    private String school;

    private String city;

//    @DBRef
    private List<EGEresults> egeResults;

    private List<String> individualAchievements;

    public Enrollee(String email, String firstName, String lastName, String patronymic, String password, Date DOB, String school, String city) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.password = password;
        this.DOB = DOB;
        this.school = school;
        this.city = city;
    }

    public Enrollee(String email, String firstName, String lastName, String patronymic, String password, Date DOB, String school, String city, List<EGEresults> egeresults, List<String> individualAchievements) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.password = password;
        this.DOB = DOB;
        this.school = school;
        this.city = city;
        this.egeResults = egeresults;
        this.individualAchievements = individualAchievements;
    }
}
