package com.example.demo.todo;

public enum Status {
	
	TODO("TODO"),
	IN_PROGRESS("IN_PROGRESS"),
	COMPLETED("COMPLETED");
	
	private String value;

	private Status(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
