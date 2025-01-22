package com.habitalign.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitalign.entities.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
}
