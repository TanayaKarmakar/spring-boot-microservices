package com.app.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.domain.BeerOrderLine;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {

}
