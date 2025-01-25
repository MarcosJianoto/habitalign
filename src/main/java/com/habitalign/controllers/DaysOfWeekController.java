package com.habitalign.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.habitalign.dto.DaysOfWeekDTO;
import com.habitalign.services.DaysOfWeekService;

@RestController
public class DaysOfWeekController {

	private final DaysOfWeekService daysOfWeekService;

	public DaysOfWeekController(DaysOfWeekService daysOfWeekService) {
		this.daysOfWeekService = daysOfWeekService;
	}

	@PostMapping("/createdaysofweek")
	public ResponseEntity<Void> saveCategory() {
		daysOfWeekService.saveDaysOfWeek();
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatedayofweek/{id}")
	public ResponseEntity<Void> updateDaysOfWeek(@RequestBody DaysOfWeekDTO daysOfWeekDTO, @PathVariable Long id) {

		daysOfWeekService.updateIsActiveDaysOfWeek(id, daysOfWeekDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deletedaysofweek")
	public ResponseEntity<Void> deleteDaysOfWeek() {

		daysOfWeekService.deleteDaysOfWeek();
		return ResponseEntity.noContent().build();
	}

}
