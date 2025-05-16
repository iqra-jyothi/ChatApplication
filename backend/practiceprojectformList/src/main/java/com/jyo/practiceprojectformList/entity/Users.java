package com.jyo.practiceprojectformList.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Users")
public class Users {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@NotBlank(message="Email is required")
@Email(message="Email is invalid")

    private String name;

@NotBlank(message="Password is required")
@Size(min=6,message="Password must be at least 6 characters")
    private String password;



//    private boolean verified;  // Add this field
//
//    // Getters and Setters
//    public boolean isVerified() {
//        return verified;
//    }
//
//    public void setVerified(boolean verified) {
//        this.verified = verified;
//    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Users(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    Users() {
    }
}
