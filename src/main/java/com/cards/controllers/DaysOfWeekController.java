package com.cards.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cards.dto.DaysOfWeekActiveDTO;
import com.cards.services.DaysOfWeekService;

@RestController
public class DaysOfWeekController {

	private final DaysOfWeekService daysOfWeekService;

	public DaysOfWeekController(DaysOfWeekService daysOfWeekService) {
		this.daysOfWeekService = daysOfWeekService;
	}

	@PostMapping("/createdaysofweek")
	public ResponseEntity<Void> saveCategory() throws Exception {
		daysOfWeekService.saveDaysOfWeek();
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatealldaystatus/{active}")
	public ResponseEntity<Void> updateAllDayStatus(@PathVariable Boolean active) {
		daysOfWeekService.updateAllDayIfActiveOrInative(active);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatedayofweek/{id}")
	public ResponseEntity<Void> updateDaysOfWeek(@RequestBody DaysOfWeekActiveDTO daysOfWeekActiveDTO,
			@PathVariable Integer id) {

		daysOfWeekService.updateIsActiveDaysOfWeek(id, daysOfWeekActiveDTO);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deletedaysofweek")
	public ResponseEntity<Void> deleteDaysOfWeek() {

		daysOfWeekService.deleteDaysOfWeek();
		return ResponseEntity.noContent().build();
	}

}
