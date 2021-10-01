package com.example.demo.todo;

import java.net.URI;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
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

	@GetMapping("/todos")
	Set<Todo> getAll(){
		return this.todoService.getAll();
	}
	
	@GetMapping("/todos/{id}")
	Todo getById(@PathVariable Long id){
		return this.todoService.getById(id);
	}
	
	@PostMapping("/todos")
	ResponseEntity<Todo>  add(@RequestBody Todo todo, HttpServletRequest request) {
		
		todoService.save(todo);
		URI uri = URI.create(request.getRequestURI() + todo.getId());
		return ResponseEntity.created(uri).body(todo);
	}
	
	@DeleteMapping("/todos/{id}")
	void remove(@PathVariable Long id) {
		this.todoService.removeById(id);
	}
}
