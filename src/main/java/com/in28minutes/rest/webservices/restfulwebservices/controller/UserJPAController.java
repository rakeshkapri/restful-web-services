package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.entity.Post;
import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.repository.UserRepository;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAController {
    @Autowired
    private UserDatabaseService userDatabaseService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/jpa/users")
    public List<User> retrieveAllUsers(){
        List<User> userList = userRepository.findAll();
        System.out.println("Size of user list -- > " + userList.size());
        return userList;
    }

    @GetMapping(value = "/jpa/users/{id}")
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

    @PostMapping(value = "/jpa/users")
    public ResponseEntity<String> saveUser(@Valid @RequestBody User user){
        User createdUser = userRepository.save(user);
        URI uri = null;
        if(Optional.ofNullable(createdUser).isPresent()){
            uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        }
        else{
            uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        }
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
       userRepository.deleteById(id);
    }

    @GetMapping(value = "/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get().getPosts();
        }
        else{
            throw new UserNotFoundException("User Not found for - " + id);
        }

    }
}
