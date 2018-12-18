package com.in28minutes.rest.webservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull
    private int id;
    @Size(min = 2, message = "name should contain min 2 characters")
    private String name;
    @Past
    private LocalDate dateOfBirth;
}
