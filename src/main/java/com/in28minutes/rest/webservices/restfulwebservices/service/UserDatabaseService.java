package com.in28minutes.rest.webservices.restfulwebservices.service;

import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDatabaseService {
    private static List<User> userList = new ArrayList();
    static{
        userList.add(User.builder().id(1).name("Mr. A").dateOfBirth(
                LocalDate.of(1983, 02, 15)).build());
        userList.add(User.builder().id(2).name("Mr. B").dateOfBirth(
                LocalDate.of(1990, 10, 20)).build());
        userList.add(User.builder().id(3).name("Mr. C").dateOfBirth(
                LocalDate.of(1975, 05, 10)).build());
    }

    public List<User> findAll(){
        return userList;
    }

    public User findOne(int id){
        return userList.get(id);
    }

    public void saveUser(User user){
        userList.add(user);
    }
}
