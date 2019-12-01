package org.vferrer.portfolio.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends Exception {

	private static final long serialVersionUID = 3172686140029467965L;

	public StockNotFoundException(String stockId) {
		super(String.format("Stock with ticket %s not found",stockId));
		
	}
}