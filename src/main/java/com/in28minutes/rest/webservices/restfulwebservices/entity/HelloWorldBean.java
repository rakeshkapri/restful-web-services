package com.in28minutes.rest.webservices.restfulwebservices.entity;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Builder
@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldBean {
    private String message;
}
