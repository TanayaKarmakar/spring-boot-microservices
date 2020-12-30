package com.app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.domain.BeerInventory;

public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {
	List<BeerInventory> findAllByBeerId(UUID beerId);
}
