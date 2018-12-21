package com.in28minutes.rest.webservices.restfulwebservices.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue
    private int postId;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
