package com.xevgnov.autowire.circular.problem.service;

import java.util.UUID;

import com.xevgnov.autowire.circular.problem.dto.Order;

public interface DeliveryService {
    
    void deliveryOrder(Order order);
    String getEstimatedDeliveryTime();

}
