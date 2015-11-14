package org.vferrer.portfolio.manager.stokker;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("stokker")
public interface StokkerClient 
{
	@RequestMapping(method = RequestMethod.GET, value = "/stockQuotationJPAs")
	List<StockQuotation> getStockQuotations();
	
	@RequestMapping(method = RequestMethod.GET, value = "/stockQuotationJPAs/search/findTopByStockOrderByTimestampDesc")
	StockQuotation getLastStockPrice(@RequestParam("ticker") String stock);
	
}
