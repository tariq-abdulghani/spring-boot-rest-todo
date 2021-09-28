package com.example.demo.todo;

import java.util.Set;

public interface TodoService {
	
	Set<Todo> getAll();
	
	Todo getById(Long id);
	
	Todo save(Todo todo);
	
	void remove(Todo todo);
	
	void removeById(Long id);
}
