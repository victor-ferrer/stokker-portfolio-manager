package org.vferrer.portfolio.manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PortfolioNotFoundException extends Exception {

	private static final long serialVersionUID = 1066730993044639679L;

	public PortfolioNotFoundException(String portfolioId) {
		super(String.format("Portfolio %s not found", portfolioId));
	}

}
