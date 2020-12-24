package com.app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.domain.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}
