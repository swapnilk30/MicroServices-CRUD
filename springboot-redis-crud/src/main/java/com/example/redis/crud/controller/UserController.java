package com.example.redis.crud.controller;

import com.example.redis.crud.dao.UserDao;
import com.example.redis.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping
    public User createUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        userDao.saveUser(user);
        return user;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return userDao.getUser(userId);
    }

    @GetMapping
    public Map<Object,Object> getAllUsers(){
        return userDao.findAll();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userDao.deleteUser(userId);
    }

}
