package com.jyo.practiceprojectformList.service;

import com.jyo.practiceprojectformList.Repository.TaskRepository;
import com.jyo.practiceprojectformList.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private  final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask( int id,Task task){
        Task existingtask=taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"+id));
        existingtask.setName(task.getName());
        existingtask.setDescription(task.getDescription());
        return taskRepository.save(existingtask);
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    public Task getTaskById(int id) {
//        return taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"+id));

        Optional<Task> byid= taskRepository.findById(id);
        Task task=byid.get();
        return task;

    }

    public List<Task> searchItemsByName(String name) {
     List<Task>item=taskRepository.findByNameContainingIgnoreCase(name);
     if(item.isEmpty()){
         throw new RuntimeException("Item not found"+name);
     }
        return item;

    }
}
