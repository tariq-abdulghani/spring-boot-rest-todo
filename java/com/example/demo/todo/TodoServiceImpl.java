package com.example.demo.todo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService{
	
	private final TodoRepository todoRepository;

	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public Set<Todo> getAll() {
		Set<Todo> todos = new HashSet<Todo>();
		todoRepository.findAll().forEach(todos :: add);
		return todos;
	}

	@Override
	public Todo getById(Long id) {
		return todoRepository.findById(id).orElse(null);
	}

	@Override
	public Todo save(Todo todo) {
		todoRepository.save(todo);
		return todo;
	}

	@Override
	public void remove(Todo todo) {
		todoRepository.delete(todo);
	}

	@Override
	public void removeById(Long id) {
		todoRepository.deleteById(id);
	}

	@Override
	public Todo patchStatus(Long id, Todo todo) {
		return todoRepository.findById(id).map(t->{
			t.setStatus(todo.getStatus());
			todoRepository.save(t);
			return t;
		}).orElseThrow(RuntimeException :: new);
	}
	
	
}
