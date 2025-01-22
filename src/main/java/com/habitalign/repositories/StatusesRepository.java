package com.habitalign.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habitalign.entities.Statuses;

@Repository
public interface StatusesRepository extends JpaRepository<Statuses, Long> {

	Optional<Statuses> findById(Integer statusId);
}
