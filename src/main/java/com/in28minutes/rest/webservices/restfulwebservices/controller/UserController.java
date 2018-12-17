package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.service.UserDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDatabaseService userDatabaseService;

    @GetMapping(value = "/users")
    public List<User> retrieveAllUsers(){
        return userDatabaseService.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user =  userDatabaseService.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id-" + id);
        }
        else{
            return user;
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        User createdUser = userDatabaseService.saveUser(user);
        URI uri = null;
        if(Optional.ofNullable(createdUser).isPresent()){
            uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        }
        else{
            uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        }
        return ResponseEntity.created(uri).build();
    }
}
