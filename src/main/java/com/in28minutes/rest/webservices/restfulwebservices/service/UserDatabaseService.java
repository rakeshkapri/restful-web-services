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
        userMap.put(1, new User(1,"Mr. A",
                LocalDate.of(1983, 02, 15)));
        userMap.put(2, new User(1,"Mr. B",
                LocalDate.of(1983, 02, 15)));
        userMap.put(3, new User(1,"Mr. C",
                LocalDate.of(1983, 02, 15)));
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

    public User deleteUser(int id){
        if(userMap.containsKey(id)){
            User user = userMap.get(id);
            userMap.remove(id);
            return user;
        }
        else{
            return null;
        }
    }
}
