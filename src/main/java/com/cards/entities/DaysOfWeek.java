package com.cards.entities;

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
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "view")
	private Boolean view;

	public DaysOfWeek(Integer l, String string) {
		// TODO Auto-generated constructor stub
	}

	public DaysOfWeek() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
