package com.application.todolist;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
	public final TaskRepository taskRepository;
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public Task saveTask(Task task) {
		return taskRepository.save(task);// insert 
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public Optional<Task> getTaskById(Long Id) {
		return taskRepository.findById(Id);
	}
	
	public Boolean deleteById(Long Id) {
		 Boolean exists = taskRepository.existsById(Id);
		 if(exists) {
			 taskRepository.deleteById(Id);
			 return true;
		 }else {
			 return false;
		 }
		 
	}
	
	public Task updateTaskById(Task incomming, Long Id) {
		
		 Optional<Task> task = taskRepository.findById(Id);// database data
		 if(task.isPresent()) {
			 task.get().setName(incomming.getName());
			 task.get().setCompeleted(incomming.getCompeleted());
			 return taskRepository.save(task.get());
		 }else {
			 return taskRepository.save(incomming);
		 }
		 
	}
}
