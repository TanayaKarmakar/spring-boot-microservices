package com.app.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerOrderPagedList extends PageImpl<BeerOrderDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5159247228309905844L;

	public BeerOrderPagedList(List<BeerOrderDto> content) {
		super(content);
	}
	
	public BeerOrderPagedList(List<BeerOrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
