package com.in28minutes.rest.webservices.restfulwebservices.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class User {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
}
