package com.xevgnov.autowire.circular.problem.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Order {

  private UUID id = UUID.randomUUID();  
  private String clientEmail;  
  private String deliveryAddress;
  private List<String> dishes;
  private UUID paymentId;
  
}
