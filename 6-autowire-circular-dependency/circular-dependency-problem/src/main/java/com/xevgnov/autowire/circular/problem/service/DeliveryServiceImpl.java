package com.xevgnov.autowire.circular.problem.service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.xevgnov.autowire.circular.problem.dto.Order;
import com.xevgnov.autowire.circular.problem.dto.Status;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private ExecutorService couriers = Executors.newFixedThreadPool(4);

    private OrderService orderService;

    public DeliveryServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void deliveryOrder(UUID id) {
        couriers.submit(() -> doDelivery(id));
    }

    private void doDelivery(UUID id) {
        Order order = orderService.getOrder(id);
        try {
            long orderDeliveryTime = ThreadLocalRandom.current().nextLong(100, 2000);
            Thread.sleep(orderDeliveryTime);
        } catch (InterruptedException e) {
            log.error("Order delivery issue. Order details: {}", order, e);
        }
        order.setStatus(Status.DELIVERED);
        log.info("Order {} is delivered", order.getId());
    }

    @PreDestroy
    private void preDestroy() {
        if (!couriers.isShutdown()) {
            couriers.shutdown();
        }
    }

}
