package org.vferrer.portfolio.manager.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String getPortfolioInvestment() {
		Portfolio portfolio = portfolioRepository.findOne(Long.parseLong("1"));

		return valuationService.findPorfolioInvestment(portfolio).toString();
	}
	
	@RequestMapping(value = "valuation", produces = "application/json")
	public String getPortfolioValuation() {
		Portfolio portfolio = portfolioRepository.findOne(Long.parseLong("1"));

		return valuationService.findPortfolioMarketValue(portfolio, new Date()).toString();
	}
	
}
