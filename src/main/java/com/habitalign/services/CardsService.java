package com.habitalign.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

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

	public void saveCard(CardsCreateDTO cardsCreateDTO) {

		DaysOfWeek daysOfWeek = dayOfWeekRepository.findById(cardsCreateDTO.getDayId())
				.orElseThrow(() -> new IllegalArgumentException("Dia da semana não encontrado"));

		Statuses statuses = statusesRepository.findById(cardsCreateDTO.getStatusId())
				.orElseThrow(() -> new IllegalArgumentException("Status não encontrado"));

		Cards card = new Cards();

		card.setTitle(cardsCreateDTO.getTitle());
		card.setDay(daysOfWeek);
		card.setStatus(statuses);
		card.setOrder(cardsCreateDTO.getOrder());
		card.setCreatedAt(LocalDateTime.now());
		card.setUpdatedAt(LocalDateTime.now());

		cardsRepository.save(card);

	}

	public void updateCardsDTO(Long id, CardsUpdateDTO cardsUpdateDTO) {

		Cards card = cardsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Card is not found"));

		DaysOfWeek daysOfWeek = dayOfWeekRepository.findById(cardsUpdateDTO.getDayId())
				.orElseThrow(() -> new IllegalArgumentException("Dia da semana não encontrado"));

		Statuses statuses = statusesRepository.findById(cardsUpdateDTO.getStatusId())
				.orElseThrow(() -> new IllegalArgumentException("Status não encontrado"));

		card.setTitle(cardsUpdateDTO.getTitle());
		card.setDay(daysOfWeek);
		card.setStatus(statuses);
		card.setOrder(cardsUpdateDTO.getOrder());
		card.setUpdatedAt(LocalDateTime.now());

		cardsRepository.save(card);

	}

	public CardsUpdateDTO getCardById(Long id) {
		Cards card = cardsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Card não encontrado"));

		CardsUpdateDTO cardsUpdateDTO = new CardsUpdateDTO();
		cardsUpdateDTO.setId(card.getId());
		cardsUpdateDTO.setTitle(card.getTitle());
		cardsUpdateDTO.setStatusId(card.getStatus().getId());
		cardsUpdateDTO.setUpdatedAt(card.getUpdatedAt());
		cardsUpdateDTO.setOrder(card.getOrder());

		return cardsUpdateDTO;
	}

	public void deleteById(Long id) {
		cardsRepository.deleteById(id);
	}

}
