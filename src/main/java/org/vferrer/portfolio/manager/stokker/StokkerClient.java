package org.vferrer.portfolio.manager.stokker;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Stokker")
public interface StokkerClient 
{
	@RequestMapping(method = RequestMethod.GET, value = "/stockQuotationJPAs")
	List<StockQuotations> getStockQuotations();

//	@RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
//	Store update(@PathVariable("storeId") Long storeId, Store store);
}
