package com.cards.services;

import org.springframework.stereotype.Service;

import com.cards.dto.StatusesDTO;
import com.cards.entities.Statuses;
import com.cards.repositories.StatusesRepository;

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

	public void updateStatuses(Integer id, StatusesDTO statusesDTO) {
		Statuses statuses = statusesRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Status not found"));

		statuses.setName(statusesDTO.getName());
	}

	public StatusesDTO getStatusesById(Integer id) {

		Statuses statuses = statusesRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Status not found"));

		StatusesDTO statusesDTO = new StatusesDTO();
		statusesDTO.setId(statuses.getId());
		statusesDTO.setName(statuses.getName());
		return statusesDTO;

	}

	public void deleteById(Integer id) {
		statusesRepository.deleteById(id);
	}

}
