package com.food.ordering.system.order.service.messaging.mapper;

import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMessagingDataMapper {

    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
        Order order = orderCreatedEvent.getOrder();

        return PaymentRequestAvroModel
                .newBuilder()
                .setOrderId(order.getId().getValue().toString())
                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .setPrice(order.getPrice().getAmount())
                .setCustomerId(order.getCustomerId().getValue().toString())
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelledEvent) {
        Order order = orderCancelledEvent.getOrder();

        return PaymentRequestAvroModel
                .newBuilder()
                .setOrderId(order.getId().getValue().toString())
                .setCreatedAt(orderCancelledEvent.getCreatedAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                .setPrice(order.getPrice().getAmount())
                .setCustomerId(order.getCustomerId().getValue().toString())
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .build();
    }
}
