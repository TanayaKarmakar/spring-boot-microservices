package com.app.services.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.web.model.v2.BeerDtoV2;
import com.app.web.model.v2.BeerStyleEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeerServiceV2Impl implements BeerServiceV2 {

	@Override
	public BeerDtoV2 getBeerById(UUID beerId) {
		return BeerDtoV2.builder().id(UUID.randomUUID())
				.beerName("Galaxy Cat")
				.beerStyle(BeerStyleEnum.ALE)
				.build();
	}

	@Override
	public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
		return BeerDtoV2.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {

	}

	@Override
	public void deleteById(UUID beerId) {
		log.info("Beer with ID " + beerId.toString() + " deleted");
	}

}
