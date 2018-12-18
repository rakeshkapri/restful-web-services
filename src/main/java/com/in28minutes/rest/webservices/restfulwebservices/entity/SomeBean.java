package com.in28minutes.rest.webservices.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;
    public SomeBean(String value1, String value2, String value3){
        this.field1 = value1;
        this.field2 = value2;
        this.field3 = value3;
    }
}
