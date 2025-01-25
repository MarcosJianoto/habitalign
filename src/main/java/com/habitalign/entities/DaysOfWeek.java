package com.habitalign.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "days_of_week")
public class DaysOfWeek {

	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "view")
	private Boolean view;

	public DaysOfWeek(long l, String string) {
		// TODO Auto-generated constructor stub
	}

	public DaysOfWeek() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

}
