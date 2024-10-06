package com.xevgnov.spring.testing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xevgnov.spring.testing.dto.FxRatesResponse;

@FeignClient(value = "fxratesapi", url = "https://api.fxratesapi.com/")
public interface FxRatesApiClient {

    /**
     * Request example
     * //https://api.fxratesapi.com/historical?date=2024-08-02&currencies=EUR&base=PLN
     */

    @RequestMapping(method = RequestMethod.GET, value = "/historical?date={date}&currencies={target}&base={base}", produces = "application/json")
    FxRatesResponse getConvertationRate(
            @PathVariable("date") String date,
            @PathVariable("target") String targetCurency,
            @PathVariable("base") String baseCurrency);

}
