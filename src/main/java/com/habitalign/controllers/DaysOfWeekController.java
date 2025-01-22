package com.habitalign.controllers;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Void> saveCategory(@RequestBody DaysOfWeekDTO daysOfWeekDTO) {

		daysOfWeekService.saveDaysOfWeek(daysOfWeekDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatedayofwek/{id}")
	public ResponseEntity<Void> updateCategory(@RequestBody DaysOfWeekDTO daysOfWeekDTO, @PathVariable Long id) {

		daysOfWeekService.updateDaysOfWeek(id, daysOfWeekDTO);
		return ResponseEntity.noContent().build();
	}

}
