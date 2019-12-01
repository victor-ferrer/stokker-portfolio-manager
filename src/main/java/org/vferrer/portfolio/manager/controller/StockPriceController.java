package org.vferrer.portfolio.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vferrer.portfolio.manager.entitties.Stock;
import org.vferrer.portfolio.manager.repositories.StockRepository;
import org.vferrer.portfolio.manager.stokker.StockQuotation;
import org.vferrer.portfolio.manager.stokker.StokkerClient;

@RestController
@RequestMapping("/stocks")
public class StockPriceController {

	@Autowired
	private StokkerClient stokkerClient;

	@Autowired 
	private StockRepository stocksRepo;
	
	@RequestMapping(value = "lastPrice", produces = "application/json")
	public String getPortfolioInvestment(@Param("stockId")String stockId) throws NumberFormatException, StockNotFoundException {
		Stock stock = stocksRepo.findById(Long.parseLong(stockId))
								.orElseThrow(() -> new StockNotFoundException(stockId));

		String ticker = stock.getTicker();
		
		if (stock.isNeedMarketSuffix()){
			ticker = ticker + "." + stock.getMarket();
		}
		
		StockQuotation stockQuotation = stokkerClient.getLastStockPrice(ticker);
		
		return stockQuotation.getValue().toString();
	}
}
