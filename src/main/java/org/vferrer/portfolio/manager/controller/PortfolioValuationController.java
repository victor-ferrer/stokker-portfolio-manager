package org.vferrer.portfolio.manager.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vferrer.portfolio.manager.entitties.Portfolio;
import org.vferrer.portfolio.manager.repositories.PortfolioRepository;
import org.vferrer.portfolio.manager.service.PortfolioValuationService;

@RestController
@RequestMapping("/portfolioValuation")
public class PortfolioValuationController {

	@Autowired
	private PortfolioRepository portfolioRepository;

	@Autowired
	private PortfolioValuationService valuationService;

	@RequestMapping(value = "investment", produces = "application/json")
	public String getPortfolioInvestment(@Param("portfolioId")String portfolioId) throws NumberFormatException, PortfolioNotFoundException {
		Portfolio portfolio = portfolioRepository.findById(Long.parseLong(portfolioId))
												 .orElseThrow(() -> new PortfolioNotFoundException(portfolioId));

		return valuationService.findPorfolioInvestment(portfolio).toString();
	}
	
	@RequestMapping(value = "valuation", produces = "application/json")
	public String getPortfolioValuation(@Param("portfolioId")String portfolioId) throws NumberFormatException, PortfolioNotFoundException {
		Portfolio portfolio = portfolioRepository.findById(Long.parseLong(portfolioId))
												 .orElseThrow(() -> new PortfolioNotFoundException(portfolioId));

		return valuationService.findPortfolioMarketValue(portfolio, new Date()).toString();
	}
	
}
