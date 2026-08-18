package com.vdgarcia.appointment_service.publisher.kafka;

import com.vdgarcia.appointment_service.publisher.inter.CreateEventPublisher;
import com.vdgarcia.events.AppointmentCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class kafkaAppointmentPublisher implements CreateEventPublisher {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${application.config.kafka.topics.appointment-created}")
    private String appointmentCreateTopic;

    @Override
    public void publishAppointmentCreated(AppointmentCreated evento) {
        log.info("Publicando evento AppointmentCreated para la cita ID: {}", evento.getAppointmentId());
        kafkaTemplate.send(appointmentCreateTopic,String.valueOf(evento.getAppointmentId()),evento);

    }
}
