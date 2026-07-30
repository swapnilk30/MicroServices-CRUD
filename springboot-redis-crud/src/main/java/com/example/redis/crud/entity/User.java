package com.example.redis.crud.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private String userId;
    private String name;
    private String password;
    private String email;
    private String phone;
}
