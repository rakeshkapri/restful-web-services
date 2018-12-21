package com.in28minutes.rest.webservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @NotNull
    @GeneratedValue
    @Id
    private int id;
    @Size(min = 2, message = "name should contain min 2 characters")
    private String name;
    @Past
    private LocalDate dateOfBirth;

    public User(int id, String name, LocalDate dateOfBirth){
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;
}
