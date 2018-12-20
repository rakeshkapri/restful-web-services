package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.Name;
import com.in28minutes.rest.webservices.restfulwebservices.entity.PersonV1;
import com.in28minutes.rest.webservices.restfulwebservices.entity.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping(value = "/person", params = "version=1")
    public PersonV1 getPersonV1(){
        return new PersonV1("Rakesh Kapri V1");
    }

    @GetMapping(value = "/person", params = "version=2")
    public PersonV2 getPersonV2(){
        Name name = new Name("Rakesh", "Kapri V2");
        return new PersonV2(name);
    }
}
