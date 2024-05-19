package com.xevgnov.autowire.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xevgnov.autowire.domain.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestaurantService {
    
    @Autowired
    private FoodService foodService;

    private PaymentService paymentService;

    private DeliveryService deliveryService;

    private ClientService clientService;

    @Autowired
    public RestaurantService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public void makeOrder(Order order){
        log.info("Starting to handle the order [{}]", order.getId());
        clientService.sendEmail(order);
        paymentService.processPayment(order);
        foodService.cookFood(order);
        deliveryService.deliver(order);
        clientService.sendSurvey(order);
        log.info("Finishing to handle the order [{}]", order.getId());
    }

    @Autowired
    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Autowired
    public void initClientService(ClientService clientService){
        this.clientService = clientService;
    }

}