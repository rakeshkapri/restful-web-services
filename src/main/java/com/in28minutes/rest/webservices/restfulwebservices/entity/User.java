package com.in28minutes.rest.webservices.restfulwebservices.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
