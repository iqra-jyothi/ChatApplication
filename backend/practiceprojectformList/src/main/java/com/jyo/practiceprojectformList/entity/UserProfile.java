package com.jyo.practiceprojectformList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
        @Id
        private String email;
        @Column(updatable = false)
        private String password;
        private String name;
        private String profilePhotoUrl; // Store photo URL or base64

        // Getters and setters
    }


