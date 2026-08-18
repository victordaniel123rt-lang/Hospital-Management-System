package com.vdgarcia.payment_service.service;

import com.vdgarcia.events.AppointmentCreated;
import com.vdgarcia.events.PaymentCompleted;
import com.vdgarcia.events.Status;
import com.vdgarcia.payment_service.dto.PaymentDTO;
import com.vdgarcia.payment_service.mapper.Mapper;
import com.vdgarcia.payment_service.model.Payment;
import com.vdgarcia.payment_service.publisher.inter.CreatedEventPublisher;
import com.vdgarcia.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository repository;
    private final CreatedEventPublisher publisher;

    @Override
    public PaymentDTO crearPago(AppointmentCreated evento) {

        Payment pago = Payment.builder()
                .appointmentId(evento.getAppointmentId())
                .patientId(evento.getPatientId())
                .amount(evento.getAmount())
                .paymentDate(LocalDate.now())
                .build();

        Payment creado = repository.save(pago);
        PaymentCompleted event = PaymentCompleted.builder()
                .appointmentId(creado.getAppointmentId())
                .paymentId(creado.getId())
                .status(Status.COMPLETED)
                .paymentDate(creado.getPaymentDate())
                .build();
        publisher.publishPaymentCreated(event);
        return Mapper.toPaymentDTO(creado);
    }
}
