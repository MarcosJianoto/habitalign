package com.habitalign.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitalign.entities.DaysOfWeek;

@Repository
public interface DaysOfWeekRepository extends JpaRepository<DaysOfWeek, Integer> {

	Optional<DaysOfWeek> findById(Integer dayId);
}
