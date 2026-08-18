package com.vdgarcia.appointment_service.mapper;

import com.vdgarcia.appointment_service.dto.AppointmentDTO;
import com.vdgarcia.appointment_service.model.Appointment;

public class Mapper {

    public static AppointmentDTO toAppointmentDTO(Appointment entity){
        if (entity==null) return null;
        return AppointmentDTO.builder()
                .id(entity.getId())
                .patientId(entity.getPatientId())
                .doctorId(entity.getDoctorId())
                .date(entity.getDate())
                .reason(entity.getReason())
                .time(entity.getTime())
                .status(entity.getStatus())
                .build();
    }


    public static Appointment toAppointment(AppointmentDTO dto){
        if (dto==null) return null;
        return Appointment.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .doctorId(dto.getDoctorId())
                .date(dto.getDate())
                .time(dto.getTime())
                .reason(dto.getReason())
                .status(dto.getStatus())
                .build();
    }


}
