package com.cards.dto;

import java.time.LocalDateTime;

public class CardsUpdateDTO {

	private Integer id;
	private String title;
	private Integer statusId;
	private LocalDateTime updatedAt;

	public CardsUpdateDTO() {
		super();

	}

	public CardsUpdateDTO(Integer id, String title, Integer dayId, Integer statusId, Integer order, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.statusId = statusId;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
