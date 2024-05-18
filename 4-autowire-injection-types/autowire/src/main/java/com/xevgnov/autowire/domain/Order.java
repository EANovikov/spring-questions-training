package com.xevgnov.autowire.service;

import java.util.UUID;

@Builder
@Getters
@Setters
public class Order {

  private UUID id = UUID.randomUUID();  
  private String clientEmail;  
  private String deliveryAddress;
  private String dish;
  private UUID paymentId;
  
}
