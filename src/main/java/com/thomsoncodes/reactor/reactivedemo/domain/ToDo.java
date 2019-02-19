package com.thomsoncodes.reactor.reactivedemo.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ToDo {
	
	private String id;
	private String description;
	private LocalDateTime created;
	private LocalDateTime modified;
	private boolean completed;
	
	public ToDo() {}
	
	public ToDo(String descrption) {
		this.description = descrption;
	}

	public ToDo(String description, boolean completed) {
		this.description = description;
		this.completed = completed;
	}
	
	
	

}
