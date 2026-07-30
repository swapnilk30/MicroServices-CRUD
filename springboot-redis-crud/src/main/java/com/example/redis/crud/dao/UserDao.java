package com.example.redis.crud.dao;

import com.example.redis.crud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    private final RedisTemplate<String, Object> redisTemplate;

    public UserDao(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String KEY = "USER";

    //Equivalent Redis command:
    //HSET USER 101 {serialized User object}
    //Stores the user in the USER hash using userId as the field.
    public User saveUser(User user) {
        redisTemplate.opsForHash().put(KEY,user.getUserId(),user);
        return user;
    }


    //Equivalent Redis command:
    //HGET USER 101
    //Returns the User object for the given ID.
    public User getUser(String userId) {
        return (User) redisTemplate.opsForHash().get(KEY,userId);
    }

    //Equivalent Redis command:
    //HGETALL USER
    //Returns all users stored in the hash.
    public Map<Object,Object> findAll() {
        return redisTemplate.opsForHash().entries(KEY);
    }

    //Equivalent Redis command:
    //HDEL USER 101
    //Deletes the specified user.
    public void deleteUser(String userId) {
        redisTemplate.opsForHash().delete(KEY,userId);
    }

    public User updateUser(String userId, User updatedUser) {
        User existingUser = (User) redisTemplate.opsForHash().get(KEY, userId);

        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        redisTemplate.opsForHash().put(KEY, userId, existingUser);

        return existingUser;
    }


    //Create/Update: HSET
    //Read one: HGET
    //Read all: HGETALL
    //Delete: HDEL
}
