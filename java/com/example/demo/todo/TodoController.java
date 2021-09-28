package com.example.demo.todo;

import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	
	private final TodoService todoService;
	
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@GetMapping("/todo")
	Set<Todo> getAll(){
		return this.todoService.getAll();
	}
	
	@GetMapping("/todo/{id}")
	Todo getById(@PathVariable Long id){
		return this.todoService.getById(id);
	}
	
	@PostMapping("/todo")
	Todo add(@RequestBody Todo todo) {
		return todoService.save(todo);
	}
	
	@DeleteMapping("/todo/{id}")
	void remove(@PathVariable Long id) {
		this.todoService.removeById(id);
	}
}
