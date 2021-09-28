package com.example.demo.todo;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
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
}
