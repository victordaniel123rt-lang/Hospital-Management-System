package com.vdgarcia.payment_service.service;

import com.vdgarcia.events.AppointmentCreated;
import com.vdgarcia.payment_service.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO crearPago(AppointmentCreated evento);

}
