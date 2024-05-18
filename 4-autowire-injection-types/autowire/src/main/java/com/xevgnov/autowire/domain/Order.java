package com.xevgnov.autowire.service;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Order {

  private UUID id = UUID.randomUUID();  
  private String clientEmail;  
  private String deliveryAddress;
  private List<String> dishes;
  private UUID paymentId;
  
}
