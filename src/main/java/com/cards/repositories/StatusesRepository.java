package com.cards.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cards.entities.Statuses;

@Repository
public interface StatusesRepository extends JpaRepository<Statuses, Integer> {

	Optional<Statuses> findById(Integer statusId);
}
