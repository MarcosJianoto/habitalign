package com.habitalign.services;

import org.springframework.stereotype.Service;

import com.habitalign.dto.DaysOfWeekDTO;
import com.habitalign.entities.DaysOfWeek;
import com.habitalign.repositories.DaysOfWeekRepository;

@Service
public class DaysOfWeekService {

	private final DaysOfWeekRepository daysOfWeekRepository;

	public DaysOfWeekService(DaysOfWeekRepository daysOfWeekRepository) {
		this.daysOfWeekRepository = daysOfWeekRepository;
	}

	public void saveDaysOfWeek(DaysOfWeekDTO daysOfWeekDTO) {

		DaysOfWeek daysOfWeek = new DaysOfWeek();
		daysOfWeek.setName(daysOfWeekDTO.getName());
		daysOfWeek.setView(daysOfWeekDTO.getView());
		daysOfWeekRepository.save(daysOfWeek);
	}

	public void updateDaysOfWeek(Long id, DaysOfWeekDTO daysOfWeekDTO) {
		DaysOfWeek daysOfWeek = daysOfWeekRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Status not found"));

		daysOfWeek.setName(daysOfWeekDTO.getName());
		daysOfWeek.setView(daysOfWeekDTO.getView());

	}

	public void deleteDaysOfWeek() {
		daysOfWeekRepository.deleteAll();
	}

}
