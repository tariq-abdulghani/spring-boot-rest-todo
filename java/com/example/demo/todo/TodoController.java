package com.example.demo.todo;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	ResponseEntity<Todo> remove(@PathVariable Long id) {
		this.todoService.removeById(id);
		return ResponseEntity.noContent().build();
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	ResponseEntity<Todo> handleNotFound(EmptyResultDataAccessException emptyResultDataAccessException) {
		// log emptyResultDataAccessException.getMessage();
		Map<String, String> respnseMap = new HashMap<String, String>();
		respnseMap.put("timestamp", LocalDate.now().toString());
		respnseMap.put("status", HttpStatus.NOT_FOUND.toString());
		respnseMap.put("error", "Not Found");
		respnseMap.put("messge", "resource not found");
		respnseMap.put("path", "some path");
		return new ResponseEntity(respnseMap, HttpStatus.NOT_FOUND);
	}
}
