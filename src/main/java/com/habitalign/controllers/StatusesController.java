package com.habitalign.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.habitalign.dto.StatusesDTO;
import com.habitalign.services.StatusesService;

@RestController
public class StatusesController {

	private final StatusesService statusesService;

	public StatusesController(StatusesService statusesService) {
		this.statusesService = statusesService;
	}

	@PostMapping("/createstatuses")
	public ResponseEntity<Void> saveStatuses(@RequestBody StatusesDTO statusesDTO) {

		statusesService.saveStatuses(statusesDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatestatuses/{id}")
	public ResponseEntity<Void> updateStatuses(@RequestBody StatusesDTO statusesDTO, @PathVariable Long id) {

		statusesService.updateStatuses(id, statusesDTO);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getstatuses/{id}")
	public ResponseEntity<StatusesDTO> getStatuses(@PathVariable Long id) {
		StatusesDTO statusesDTO = statusesService.getStatusesById(id);
		return ResponseEntity.ok().body(statusesDTO);
	}

	@DeleteMapping("/deletestatuses/{id}")
	public void deleteStatuses(@PathVariable Long id) {
		statusesService.deleteById(id);
	}

}
