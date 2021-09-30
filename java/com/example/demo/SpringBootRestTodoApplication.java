package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootRestTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestTodoApplication.class, args);
	}
	
	@GetMapping("/")
	public String hello(@RequestParam(value = "name") String name) {
		return "hello world spring " + name;
	}
}
