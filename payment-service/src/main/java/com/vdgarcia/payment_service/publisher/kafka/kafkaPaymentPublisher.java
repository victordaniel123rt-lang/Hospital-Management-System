package com.vdgarcia.payment_service.publisher.kafka;

import com.vdgarcia.events.PaymentCompleted;
import com.vdgarcia.payment_service.publisher.inter.CreatedEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class kafkaPaymentPublisher implements CreatedEventPublisher {

    private final KafkaTemplate<String,Object> kafkaTemplate;
    @Value("${application.config.kafka.topics.payment-created}")
    private String paymentCreateTopic;

    @Override
    public void publishPaymentCreated(PaymentCompleted evento) {
    log.info("Publicando evento en Payment-Created para la cita ID: {}", evento.getAppointmentId());
    kafkaTemplate.send(paymentCreateTopic,String.valueOf(evento.getPaymentId()),evento);
    }
}
