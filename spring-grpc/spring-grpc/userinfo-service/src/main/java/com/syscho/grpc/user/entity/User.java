package com.syscho.grpc.user.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private String userId;
    private String emailId;
    private String name;
    private int age;
    private String gender;
    private String genre;
}
