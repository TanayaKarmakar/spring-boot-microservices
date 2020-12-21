package com.app.services;

import java.util.UUID;

import com.app.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);
}
