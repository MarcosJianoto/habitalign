package com.habitalign.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Void> updateCategory(@RequestBody CardsUpdateDTO cardsUpdateDTO, @PathVariable Long id) {

		cardsService.updateCardsDTO(id, cardsUpdateDTO);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/getcard/{id}")
	public ResponseEntity<CardsUpdateDTO> getCategory(@PathVariable Long id) {
		CardsUpdateDTO cardsUpdateDTO = cardsService.getCardById(id);
		return ResponseEntity.ok().body(cardsUpdateDTO);
	}

	@DeleteMapping("/deletecard/{id}")
	public void deleteCard(@PathVariable Long id) {
		cardsService.deleteById(id);
	}

}
