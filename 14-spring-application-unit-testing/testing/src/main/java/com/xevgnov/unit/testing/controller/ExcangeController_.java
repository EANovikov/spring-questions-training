// package com.xevgnov.unit.testing.testing.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.xevgnov.unit.testing.testing.ConvertationAdvice;
// import com.xevgnov.unit.testing.testing.service.CurrencyService;
// import jakarta.validation;

// //https://fxratesapi.com/?ref=public_apis
// //https://api.fxratesapi.com/historical?date=2020-12-10&currencies=EUR
// @Validated
// @RestController
// public class ExcangeController {

//     private final CurrencyService currencyService;

//     @Autowired
//     public ExcangeController(CurrencyService currencyService){
//         this.currencyService = currencyService;
//     }

//     /* Check if it is benificial to convert your money right now */
//     @GetMapping("/{currency}")
//     public ConvertationAdvice getAdvice(
//         @PathVariable @Pattern(regexp = "\\w{3,3}",
//     message = “Invalid currency code: please provide 3 characters”) String currency){
//         currencyService.getAdvice();
//     }

// }
