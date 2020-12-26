package com.app.web.mappers;

import org.mapstruct.Mapper;

import com.app.domain.Beer;
import com.app.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	BeerDto beerToBeerDto(Beer beer);
	
	Beer beerDtoToBeer(BeerDto beerDto);
}
