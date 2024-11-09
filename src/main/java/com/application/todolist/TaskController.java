package com.application.todolist;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	public final TaskService taskService;
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	@PostMapping
	public ResponseEntity<Task> saveTask(@RequestBody Task task){
		return ResponseEntity.ok(taskService.saveTask(task));
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks(){
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") Long Id){
		Optional<Task> task = taskService.getTaskById(Id);
		
		if(task.isPresent()) {
			 return ResponseEntity.ok(task.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long Id){
		if(taskService.deleteById(Id)) {
			return ResponseEntity.ok("Deleted Successfully!");
		}else {
		return ResponseEntity.ok("Task not found");
		
	}
 }
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateById(@PathVariable("id") Long Id, @RequestBody Task task){
		return ResponseEntity.ok(taskService.updateTaskById(task, Id));
	}


}
