package com.habitalign.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.habitalign.dto.CardUpdateDayIdDTO;
import com.habitalign.dto.CardUpdatePositionColumnIdDTO;
import com.habitalign.dto.CardsCreateDTO;
import com.habitalign.dto.CardsUpdateDTO;
import com.habitalign.entities.Cards;
import com.habitalign.entities.DaysOfWeek;
import com.habitalign.entities.Statuses;
import com.habitalign.repositories.CardsRepository;
import com.habitalign.repositories.DaysOfWeekRepository;
import com.habitalign.repositories.StatusesRepository;

@Service
public class CardsService {

	private final CardsRepository cardsRepository;
	private final DaysOfWeekRepository dayOfWeekRepository;
	private final StatusesRepository statusesRepository;

	public CardsService(CardsRepository cardsRepository, DaysOfWeekRepository daysOfWeekRepository,
			StatusesRepository statusesRepository) {
		this.cardsRepository = cardsRepository;
		this.dayOfWeekRepository = daysOfWeekRepository;
		this.statusesRepository = statusesRepository;
	}

	private DaysOfWeek findDaysOfWeekById(Integer dayId) {
		return dayOfWeekRepository.findById(dayId)
				.orElseThrow(() -> new IllegalArgumentException("Day of Week not found"));
	}

	private Statuses findStatusById(Integer statusId) {
		return statusesRepository.findById(statusId)
				.orElseThrow(() -> new IllegalArgumentException("Statuses not found"));
	}

	private Cards findCardById(Integer cardId) {
		return cardsRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found"));
	}

	public void saveCard(CardsCreateDTO cardsCreateDTO) {
		DaysOfWeek daysOfWeek = findDaysOfWeekById(cardsCreateDTO.getDayId());
		Statuses statuses = findStatusById(cardsCreateDTO.getStatusId());

		Cards card = new Cards();

		card.setTitle(cardsCreateDTO.getTitle());
		card.setDay(daysOfWeek);
		card.setStatus(statuses);
		card.setOrder(cardsCreateDTO.getOrder());
		card.setCreatedAt(LocalDateTime.now());
		card.setUpdatedAt(LocalDateTime.now());

		cardsRepository.save(card);
	}

	public void updateCardsDTO(Integer id, CardsUpdateDTO cardsUpdateDTO) {

		Cards card = findCardById(id);
		Boolean updated = false;

		if (cardsUpdateDTO.getTitle() != null) {
			card.setTitle(cardsUpdateDTO.getTitle());
			updated = true;
		}

		if (cardsUpdateDTO.getStatusId() != null) {
			Statuses statuses = findStatusById(cardsUpdateDTO.getStatusId());
			card.setStatus(statuses);
			updated = true;
		}

		if (updated) {
			card.setUpdatedAt(LocalDateTime.now());
			cardsRepository.save(card);
		}

	}

	// Move card between position in same column
	public void updatePositionCardInColumn(Integer id, CardUpdatePositionColumnIdDTO cardUpdatePositionColumnIdDTO) {

		Cards card = findCardById(id);

		if (cardUpdatePositionColumnIdDTO.getPositionId() != card.getOrder()) {
			card.setOrder(cardUpdatePositionColumnIdDTO.getPositionId());
			card.setUpdatedAt(LocalDateTime.now());
			cardsRepository.save(card);
		}

	}

	// Move card between columns
	public void updateDayIdCard(Integer id, CardUpdateDayIdDTO cardUpdateDayIdDTO) {

		Cards card = findCardById(id);

		if (!cardUpdateDayIdDTO.getDayId().equals(card.getDay().getId())) {
			DaysOfWeek dayOfWeek = findDaysOfWeekById(cardUpdateDayIdDTO.getDayId());
			card.setDay(dayOfWeek);
			card.setUpdatedAt(LocalDateTime.now());
			cardsRepository.save(card);
		}

	}

	public CardsCreateDTO getCardById(Integer id) {
		Cards card = findCardById(id);

		CardsCreateDTO cardsCreateDTO = new CardsCreateDTO();
		cardsCreateDTO.setId(card.getId());
		cardsCreateDTO.setTitle(card.getTitle());
		cardsCreateDTO.setDayId(card.getDay().getId());
		cardsCreateDTO.setStatusId(card.getStatus().getId());
		cardsCreateDTO.setUpdatedAt(card.getUpdatedAt());
		cardsCreateDTO.setOrder(card.getOrder());

		return cardsCreateDTO;
	}

	public void deleteById(Integer id) {
		cardsRepository.deleteById(id);
	}

}
