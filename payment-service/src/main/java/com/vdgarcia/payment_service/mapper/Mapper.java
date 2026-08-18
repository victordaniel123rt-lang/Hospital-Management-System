package com.vdgarcia.payment_service.mapper;

import com.vdgarcia.payment_service.dto.PaymentDTO;
import com.vdgarcia.payment_service.model.Payment;

public class Mapper {

    public static PaymentDTO toPaymentDTO(Payment entity){
        if (entity==null) return null;
        return PaymentDTO.builder()
                .id(entity.getId())
                .patientId(entity.getPatientId())
                .appointmentId(entity.getAppointmentId())
                .amount(entity.getAmount())
                .paymentDate(entity.getPaymentDate())
                .build();
    }



    public static Payment toPaymentDTO(PaymentDTO dto){
        if (dto==null) return null;
        return Payment.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .appointmentId(dto.getAppointmentId())
                .amount(dto.getAmount())
                .paymentDate(dto.getPaymentDate())
                .build();
    }

}
