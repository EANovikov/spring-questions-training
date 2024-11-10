package com.xevgnov.autowire.circular.problem.service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.xevgnov.autowire.circular.problem.dto.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
public class ProcessingServiceImpl implements ProcessingService {

    @Override
    public void procces(Order order) {
        try {
            long orderProcessingTime = ThreadLocalRandom.current().nextLong(100, 2000);
            Thread.sleep(orderProcessingTime);
            log.info("Order {} processed successfully", order.getId());
        } catch (InterruptedException e) {
            log.error("Order proccessing issue. Order details: {}", order, e);
        }

    }

}
