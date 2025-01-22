package com.habitalign.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cards_sequence")
	@SequenceGenerator(name = "cards_sequence", sequenceName = "cards_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "day_id", nullable = false)
	private DaysOfWeek day;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Statuses statuses;

	@Column(name = "\"order\"", nullable = false)
	private Integer order; 

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

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

	public DaysOfWeek getDay() {
		return day;
	}

	public void setDay(DaysOfWeek day) {
		this.day = day;
	}

	public Statuses getStatus() {
		return statuses;
	}

	public void setStatus(Statuses statuses) {
		this.statuses = statuses;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
