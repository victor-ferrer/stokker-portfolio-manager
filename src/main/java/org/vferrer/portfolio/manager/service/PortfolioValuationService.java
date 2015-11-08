package org.vferrer.portfolio.manager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vferrer.portfolio.manager.entitties.Portfolio;
import org.vferrer.portfolio.manager.entitties.Position;
import org.vferrer.portfolio.manager.entitties.TradeType;
import org.vferrer.portfolio.manager.stokker.StokkerClient;

@Service
public class PortfolioValuationService {
	
	@Autowired
	private StokkerClient stokkerClient;
	
	/**
	 * Gets the portfolio market value at a current point in time.
	 * If the date is not specified, the last available date will be taken
	 * If the date is smaller than the incorporation date, an InvalidArgumentException will be thrown
	 * @param portfolio
	 * @param targetDate 
	 * @return
	 */
	// TODO: We have to consider different currencies
	public Double findPortfolioMarketValue(Portfolio portfolio, Date targetDate) {
		Double value = 0d;

		for (Position position : portfolio.getPositions()){
			String request = position.getStock().getTicker();
			if (position.getStock().isNeedMarketSuffix()){
				request = request + "." + position.getStock().getMarket();
			}
			value += position.getAmount() * stokkerClient.getLastStockPrice(request).getValue();
		}
		
		return value;
	}

	/**
	 * Returns the amount of money invested in a portfolio
	 * @param portfolio
	 * @return
	 */
	// TODO: We have to consider different currencies
	public Double findPorfolioInvestment(Portfolio portfolio) 
	{
		Double value = 0d;
		
		for (Position position : portfolio.getPositions()) 
		{
			// TODO: Only long positions are considered for now
			if (position.getTradeType() == TradeType.SHORT){
				System.out.println("Warning: SHORT positions not considered in the portfolio valuation!");
				continue;
			}
			
			if (position.getStock().getCurrency().equals(portfolio.getCurrency()))
			{
				value += position.getAmount() * position.getPrice();
			}
			else {
				// FIXME
				System.out.println("Warning: Only positions in the same currency of the portfolio are considered for valuation!");
			}
		}
		
		return value;
	}

}
