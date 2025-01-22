package com.habitalign;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.habitalign.dto.CardsCreateDTO;
import com.habitalign.dto.CardsUpdateDTO;
import com.habitalign.entities.Cards;
import com.habitalign.entities.DaysOfWeek;
import com.habitalign.entities.Statuses;
import com.habitalign.repositories.CardsRepository;
import com.habitalign.repositories.DaysOfWeekRepository;
import com.habitalign.repositories.StatusesRepository;
import com.habitalign.services.CardsService;

@ExtendWith(MockitoExtension.class)
public class CardsServiceTest {

	@Mock
	private CardsRepository cardsRepository;

	@Mock
	private DaysOfWeekRepository dayOfWeekRepository;

	@Mock
	private StatusesRepository statusesRepository;

	@InjectMocks
	private CardsService cardsService;

	@Test
	void createCardTest() {

		CardsCreateDTO dto = new CardsCreateDTO();
		dto.setTitle("New Card");
		dto.setDayId(1L);
		dto.setStatusId(2L);
		dto.setOrder(1);

		DaysOfWeek mockDay = new DaysOfWeek();
		mockDay.setId(1L);
		mockDay.setName("Monday");

		Statuses mockStatuses = new Statuses();
		mockStatuses.setId(1L);
		mockStatuses.setName("Em Progresso");

		Mockito.when(dayOfWeekRepository.findById(1L)).thenReturn(Optional.of(mockDay));
		Mockito.when(statusesRepository.findById(2L)).thenReturn(Optional.of(mockStatuses));
		Mockito.when(cardsRepository.save(Mockito.any(Cards.class))).thenAnswer(invocation -> {
			Cards card = invocation.getArgument(0);
			card.setId(1L);
			return card;
		});

		cardsService.saveCard(dto);

		Mockito.verify(cardsRepository, Mockito.times(1)).save(Mockito.any(Cards.class));
	}

	@Test
	void updateCardTest() {

		Long cardId = 1L;
		CardsUpdateDTO dto = new CardsUpdateDTO();
		dto.setTitle("Updated Card Title");
		dto.setDayId(2L);
		dto.setStatusId(3L);
		dto.setOrder(2);

		Cards existingCard = new Cards();
		existingCard.setId(cardId);
		existingCard.setTitle("Old Card Title");
		existingCard.setDay(new DaysOfWeek(1L, "Monday"));
		existingCard.setStatus(new Statuses(1L, "In Progress"));
		existingCard.setOrder(1);

		DaysOfWeek updatedDay = new DaysOfWeek();
		updatedDay.setId(2L);
		updatedDay.setName("Tuesday");

		Statuses updatedStatus = new Statuses();
		updatedStatus.setId(3L);
		updatedStatus.setName("Completed");

		Mockito.when(cardsRepository.findById(cardId)).thenReturn(Optional.of(existingCard));
		Mockito.when(dayOfWeekRepository.findById(dto.getDayId())).thenReturn(Optional.of(updatedDay));
		Mockito.when(statusesRepository.findById(dto.getStatusId())).thenReturn(Optional.of(updatedStatus));
		Mockito.when(cardsRepository.save(Mockito.any(Cards.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		cardsService.updateCardsDTO(cardId, dto);

		Mockito.verify(cardsRepository, Mockito.times(1)).findById(cardId);
		Mockito.verify(dayOfWeekRepository, Mockito.times(1)).findById(dto.getDayId());
		Mockito.verify(statusesRepository, Mockito.times(1)).findById(dto.getStatusId());
		Mockito.verify(cardsRepository, Mockito.times(1)).save(Mockito.any(Cards.class));

		Mockito.verify(cardsRepository)
				.save(Mockito.argThat(card -> card.getId().equals(cardId) && card.getTitle().equals(dto.getTitle())
						&& card.getDay().equals(updatedDay) && card.getStatus().equals(updatedStatus)
						&& card.getOrder() == dto.getOrder()));
	}

	@Test
	void getCardById_ShouldReturnCardsUpdateDTO_WhenCardExists() {
		Long cardId = 1L;

		Cards card = new Cards();
		card.setId(cardId);
		card.setTitle("Test Card");
		card.setDay(new DaysOfWeek(1L, "Monday"));
		card.setStatus(new Statuses(2L, "In Progress"));
		card.setUpdatedAt(LocalDateTime.now());
		card.setOrder(1);

		Mockito.when(cardsRepository.findById(cardId)).thenReturn(Optional.of(card));

		CardsUpdateDTO result = cardsService.getCardById(cardId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(card.getId(), result.getId());
		Assertions.assertEquals(card.getTitle(), result.getTitle());
		Assertions.assertEquals(card.getDay().getId(), result.getDayId());
		Assertions.assertEquals(card.getStatus().getId(), result.getStatusId());
		Assertions.assertEquals(card.getUpdatedAt(), result.getUpdatedAt());
		Assertions.assertEquals(card.getOrder(), result.getOrder());

		Mockito.verify(cardsRepository, Mockito.times(1)).findById(cardId);
	}

	@Test
	void getCardById_ShouldThrowException_WhenCardDoesNotExist() {
		Long cardId = 1L;

		Mockito.when(cardsRepository.findById(cardId)).thenReturn(Optional.empty());

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			cardsService.getCardById(cardId);
		});

		Mockito.verify(cardsRepository, Mockito.times(1)).findById(cardId);
	}

	@Test
	void deleteById_ShouldCallRepository_WhenIdExists() {
		Long cardId = 1L;

		Mockito.doNothing().when(cardsRepository).deleteById(cardId);

		cardsService.deleteById(cardId);

		Mockito.verify(cardsRepository, Mockito.times(1)).deleteById(cardId);
	}

	@Test
	void deleteById_ShouldThrowException_WhenIdDoesNotExist() {
		Long cardId = 1L;

		Mockito.doThrow(new EmptyResultDataAccessException(1)).when(cardsRepository).deleteById(cardId);

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			cardsService.deleteById(cardId);
		});

		Mockito.verify(cardsRepository, Mockito.times(1)).deleteById(cardId);
	}

}
