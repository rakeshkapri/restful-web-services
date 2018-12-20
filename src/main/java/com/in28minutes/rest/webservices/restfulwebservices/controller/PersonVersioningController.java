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

    @GetMapping(value = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getHeaderV1(){
        return new PersonV1("Rakesh Kapri H1");
    }

    @GetMapping(value = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getHeaderV2(){
        Name name = new Name("Rakesh", "Kapri H2");
        return new PersonV2(name);
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getContentV1(){
        return new PersonV1("Rakesh Kapri V1");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getContentV2(){
        Name name = new Name("Rakesh", "Kapri V2");
        return new PersonV2(name);
    }
}
