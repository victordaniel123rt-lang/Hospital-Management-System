package com.vdgarcia.appointment_service.listener;

import com.vdgarcia.appointment_service.service.AppointmentService;
import com.vdgarcia.events.PaymentCompleted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentListener {

    private final AppointmentService service;
    @KafkaListener(
            topics ="${application.config.kafka.topics.payment-created}",
            groupId ="${spring.kafka.consumer.group-id}"
    )
    public void handlePaymentComplete(PaymentCompleted event){
        log.info("Evento recibido en Appointment Service para la cit: {}", event.getAppointmentId());
        service.cambiarEstado(event.getAppointmentId());
    }

}
