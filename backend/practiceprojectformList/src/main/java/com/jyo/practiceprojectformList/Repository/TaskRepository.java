package com.jyo.practiceprojectformList.Repository;

import com.jyo.practiceprojectformList.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByNameContainingIgnoreCase(String name);

}
