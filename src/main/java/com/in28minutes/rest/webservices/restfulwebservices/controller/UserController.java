package com.in28minutes.rest.webservices.restfulwebservices.controller;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.service.UserDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user =  userDatabaseService.findOne(id);
        if(user==null){
            throw new UserNotFoundException("user not found id-" + id);
        }
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user){
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

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userDatabaseService.deleteUser(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }
    }
}
