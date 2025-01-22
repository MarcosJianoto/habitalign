package com.habitalign.dto;

import java.time.LocalDateTime;

public class CardsUpdateDTO {

	private Long id;
	private String title;
	private Long dayId;
	private Long statusId;
	private Integer order;
	private LocalDateTime updatedAt;

	public CardsUpdateDTO() {
		super();

	}

	public CardsUpdateDTO(Long id, String title, Long dayId, Long statusId, Integer order, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.dayId = dayId;
		this.statusId = statusId;
		this.order = order;
		this.updatedAt = updatedAt;
	}

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

	public Long getDayId() {
		return dayId;
	}

	public void setDayId(Long dayId) {
		this.dayId = dayId;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
