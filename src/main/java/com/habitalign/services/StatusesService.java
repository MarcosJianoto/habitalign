package com.habitalign.services;

import org.springframework.stereotype.Service;

import com.habitalign.dto.StatusesDTO;
import com.habitalign.entities.Statuses;
import com.habitalign.repositories.StatusesRepository;

@Service
public class StatusesService {

	private final StatusesRepository statusesRepository;

	public StatusesService(StatusesRepository statusesRepository) {

		this.statusesRepository = statusesRepository;
	}

	public void saveStatuses(StatusesDTO statusesDTO) {

		Statuses statuses = new Statuses();
		statuses.setName(statusesDTO.getName());
		statusesRepository.save(statuses);
	}

	public void updateStatuses(Long id, StatusesDTO statusesDTO) {
		Statuses statuses = statusesRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Status not found"));

		statuses.setName(statusesDTO.getName());
	}

	public StatusesDTO getStatusesById(Long id) {

		Statuses statuses = statusesRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Status not found"));

		StatusesDTO statusesDTO = new StatusesDTO();
		statusesDTO.setId(statuses.getId());
		statusesDTO.setName(statuses.getName());
		return statusesDTO;

	}

	public void deleteById(Long id) {
		statusesRepository.deleteById(id);
	}

}
