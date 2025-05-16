package com.jyo.practiceprojectformList.Repository;

import com.jyo.practiceprojectformList.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByName(String name);
// Optional< Users> findByEmail(String name);
    boolean existsByName(String name);

}
