package com.in28minutes.rest.webservices.restfulwebservices.service;

import com.in28minutes.rest.webservices.restfulwebservices.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDatabaseService {
    private static Map<Integer, User> userMap = new HashMap<>();
    static{
        userMap.put(1, User.builder().id(1).name("Mr. A").dateOfBirth(
                LocalDate.of(1983, 02, 15)).build());
        userMap.put(2, User.builder().id(2).name("Mr. B").dateOfBirth(
                LocalDate.of(1990, 10, 20)).build());
        userMap.put(3, User.builder().id(3).name("Mr. C").dateOfBirth(
                LocalDate.of(1975, 05, 10)).build());
    }

    public List<User> findAll(){
        return userMap.values().stream().collect(Collectors.toList());
    }

    public User findOne(int id){
        return userMap.get(id);
    }

    public User saveUser(User user){
        if(!Optional.ofNullable(userMap.get(user.getId())).isPresent()){
            userMap.put(user.getId(), user);
            return user;
        }
        else{
            return null;
        }
    }
}
