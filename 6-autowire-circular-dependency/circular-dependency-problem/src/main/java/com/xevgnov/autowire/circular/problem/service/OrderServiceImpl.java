package com.xevgnov.autowire.circular.problem.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Service;

import com.xevgnov.autowire.circular.problem.dto.Order;

@Service
public class OrderServiceImpl implements OrderService {
   
   private Map<UUID,Order> orders = new ConcurrentHashMap<>();
   private ProcessingService processingService;
 
    public OrderServiceImpl(ProcessingService processingService) {
      this.processingService = processingService;
   }

   @Override
   public UUID placeOrder(Order order){
    orders.put(order.getId(), order);
    processingService.procces(order); 
    return order.getId();
   }

   @Override
   public Order getOrder(UUID id){
       return orders.get(id);
   }


}
