package com.habitalign.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.habitalign.dto.CardUpdateDayIdDTO;
import com.habitalign.dto.CardUpdatePositionColumnIdDTO;
import com.habitalign.dto.CardsCreateDTO;
import com.habitalign.dto.CardsUpdateDTO;
import com.habitalign.services.CardsService;

@RestController
public class CardsController {

	private final CardsService cardsService;

	public CardsController(CardsService cardsService) {
		this.cardsService = cardsService;
	}

	@PostMapping("/createcard")
	public ResponseEntity<Void> saveCategory(@RequestBody CardsCreateDTO cardsCreateDTO) {

		cardsService.saveCard(cardsCreateDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatecard/{id}")
	public ResponseEntity<Void> updateCard(@RequestBody CardsUpdateDTO cardsUpdateDTO, @PathVariable Integer id) {

		cardsService.updateCardsDTO(id, cardsUpdateDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatepositioncardbetweencolumn/{id}")
	public ResponseEntity<Void> updatePositionCardInColumn(
			@RequestBody CardUpdatePositionColumnIdDTO cardUpdatePositionColumnIdDTO, @PathVariable Integer id) {

		cardsService.updatePositionCardInColumn(id, cardUpdatePositionColumnIdDTO);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/updatedayidcard/{id}")
	public ResponseEntity<Void> updateDayIdCard(@RequestBody CardUpdateDayIdDTO cardUpdateDayIdDTO,
			@PathVariable Integer id) {

		cardsService.updateDayIdCard(id, cardUpdateDayIdDTO);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getcard/{id}")
	public ResponseEntity<CardsCreateDTO> getCategory(@PathVariable Integer id) {
		CardsCreateDTO cardsCreateDTO = cardsService.getCardById(id);
		return ResponseEntity.ok().body(cardsCreateDTO);
	}

	@DeleteMapping("/deletecard/{id}")
	public void deleteCard(@PathVariable Integer id) {
		cardsService.deleteById(id);
	}

}
