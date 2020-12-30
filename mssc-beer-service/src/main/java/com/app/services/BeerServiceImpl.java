package com.app.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.app.domain.Beer;
import com.app.repositories.BeerRepository;
import com.app.web.controller.NotFoundException;
import com.app.web.mappers.BeerMapper;
import com.app.web.model.BeerDto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerDto getById(UUID beerId) {
		return beerMapper.beerToBeerDto(
				beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
		);
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		Beer beer = beerMapper.beerDtoToBeer(beerDto);
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc().toString());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

}
