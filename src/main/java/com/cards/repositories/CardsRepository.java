package com.cards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cards.entities.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Integer> {
}
