package com.example.demo.todo;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
	
	private final TodoService todoService;
	
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@PatchMapping("/todos/{id}")
	Todo patchStatus(@PathVariable(name = "id") Long id ,@RequestBody Todo todo) {
		return todoService.patchStatus(id, todo);
	}
	
	@DeleteMapping("/todos/{id}")
	ResponseEntity<Todo> remove(@PathVariable Long id) {
		this.todoService.removeById(id);
		return ResponseEntity.noContent().build();
	}
	
//	@ExceptionHandler({EmptyResultDataAccessException.class})
//	ResponseEntity<Todo> handleNotFound(EmptyResultDataAccessException emptyResultDataAccessException) {
//		// log emptyResultDataAccessException.getMessage();
//		Map<String, String> respnseMap = new HashMap<String, String>();
//		respnseMap.put("timestamp", LocalDate.now().toString());
//		respnseMap.put("status", HttpStatus.NOT_FOUND.toString());
//		respnseMap.put("error", "Not Found");
//		respnseMap.put("messge", "resource not found");
//		respnseMap.put("path", "some path");
//		return new ResponseEntity(respnseMap, HttpStatus.NOT_FOUND);
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	Map<String, String> handleNotFound(EmptyResultDataAccessException emptyResultDataAccessException,
			HttpServletRequest httpServletRequest) {
		// log emptyResultDataAccessException.getMessage();
		Map<String, String> responseErrorMap = new HashMap<String, String>();
		responseErrorMap.put("timestamp", LocalDateTime.now().toString());
		responseErrorMap.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
		responseErrorMap.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
		responseErrorMap.put("messege", "resource not found");
		responseErrorMap.put("path", httpServletRequest.getRequestURI());
		return responseErrorMap;
	}
}
