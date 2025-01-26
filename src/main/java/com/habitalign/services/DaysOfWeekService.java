package com.habitalign.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.habitalign.dto.DaysOfWeekActiveDTO;
import com.habitalign.entities.DaysOfWeek;
import com.habitalign.entities.DaysOfWeekEnum;
import com.habitalign.repositories.DaysOfWeekRepository;

@Service
public class DaysOfWeekService {

	private final DaysOfWeekRepository daysOfWeekRepository;

	public DaysOfWeekService(DaysOfWeekRepository daysOfWeekRepository) {
		this.daysOfWeekRepository = daysOfWeekRepository;
	}

	public void saveDaysOfWeek() throws Exception {

		List<DaysOfWeek> listDaysOfWeek = daysOfWeekRepository.findAll();

		if (!listDaysOfWeek.isEmpty()) {
			throw new Exception("List week was created, don't possible create a new");
		}

		DaysOfWeekEnum[] daysOfWeekEnum = DaysOfWeekEnum.values();

		Integer idDayWeek = 1;
		for (DaysOfWeekEnum day : daysOfWeekEnum) {

			String dayName = day.name();
			DaysOfWeek daysOfWeek = new DaysOfWeek();
			daysOfWeek.setId(idDayWeek);
			idDayWeek++;
			daysOfWeek.setName(dayName);
			daysOfWeek.setView(true);
			daysOfWeekRepository.save(daysOfWeek);

		}

	}

	public void updateAllDayIfActiveOrInative(Boolean active) {

		List<DaysOfWeek> listDaysOfWeek = daysOfWeekRepository.findAll();
		for (DaysOfWeek day : listDaysOfWeek) {
			day.setView(active);
		}

		daysOfWeekRepository.saveAll(listDaysOfWeek);

	}

	public void updateIsActiveDaysOfWeek(Integer id, DaysOfWeekActiveDTO daysOfWeekActiveDTO) {
		DaysOfWeek daysOfWeek = daysOfWeekRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID day not found"));

		daysOfWeek.setView(daysOfWeekActiveDTO.getView());
		daysOfWeekRepository.save(daysOfWeek);

	}

	public void deleteDaysOfWeek() {
		daysOfWeekRepository.deleteAll();
	}

}
