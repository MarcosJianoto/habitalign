package com.habitalign.services;

import java.util.List;

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

	public void saveDaysOfWeek() {

		List<DaysOfWeek> listDaysOfWeek = daysOfWeekRepository.findAll();
		Integer sum = 0;

		for (int i = 0; i < listDaysOfWeek.size(); i++) {
			sum++;
		}

		try {
			if (sum > 0) {
				throw new Exception("List week was created, don't possible create a new");
			}

			Object[][] daysOfWeekWithView = new Object[][] { { "Monday", true }, { "Tuesday", true },
					{ "Wednesday", true }, { "Thursday", true }, { "Friday", true }, { "Saturday", true },
					{ "Sunday", true } };

			Long numberWeek = 1L;
			for (Object[] day : daysOfWeekWithView) {

				String dayName = (String) day[0];
				Boolean view = (Boolean) day[1];
				DaysOfWeek daysOfWeek = new DaysOfWeek();
				daysOfWeek.setId(numberWeek);
				numberWeek++;
				daysOfWeek.setName(dayName);
				daysOfWeek.setView(view);
				daysOfWeekRepository.save(daysOfWeek);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateIsActiveDaysOfWeek(Long id, DaysOfWeekDTO daysOfWeekDTO) {
		DaysOfWeek daysOfWeek = daysOfWeekRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Status not found"));

		daysOfWeek.setView(daysOfWeekDTO.getView());

	}

	public void deleteDaysOfWeek() {
		daysOfWeekRepository.deleteAll();
	}

}
