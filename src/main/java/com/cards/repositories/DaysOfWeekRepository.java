package com.cards.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cards.entities.DaysOfWeek;

@Repository
public interface DaysOfWeekRepository extends JpaRepository<DaysOfWeek, Integer> {

	Optional<DaysOfWeek> findById(Integer dayId);
}
