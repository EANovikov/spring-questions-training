package com.xevgnov.autowire.circular.problem.service;

import java.util.UUID;

public interface OrderService {

   UUID placeOrder(Order order);
   Order getOrder(UUID id);

}
