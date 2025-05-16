package com.jyo.practiceprojectformList.Repository;

import com.jyo.practiceprojectformList.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    // JpaRepository provides the necessary CRUD operations (save, findById, etc.)
    // You can add custom queries here if needed
    List<UserProfile> findByNameContainingIgnoreCase(String name);
    UserProfile findByEmail(String email);

}
