package com.example.demo.todo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;


@Entity
@Table(name = "todos")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	@Column(name = "last_modified")
	private LocalDate lastModified;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	public LocalDate getLastModified() {
		return lastModified;
	}

	@PostPersist
	private void init() {
		this.creationDate = LocalDate.now();
		this.lastModified = LocalDate.now();
		this.status = Status.TODO;
	}
	
	@PostUpdate
	private void update() {
		this.lastModified = LocalDate.now();
	}
	
}
