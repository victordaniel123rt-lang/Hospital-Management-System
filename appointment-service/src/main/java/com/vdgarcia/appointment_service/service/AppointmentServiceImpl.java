package com.vdgarcia.appointment_service.service;

import com.vdgarcia.appointment_service.cliente.dtos.DoctorDTO;
import com.vdgarcia.appointment_service.cliente.dtos.PatientDTO;
import com.vdgarcia.appointment_service.cliente.inter.DoctorClient;
import com.vdgarcia.appointment_service.cliente.inter.PatientClient;
import com.vdgarcia.appointment_service.dto.AppointmentDTO;
import com.vdgarcia.appointment_service.mapper.Mapper;
import com.vdgarcia.appointment_service.model.Appointment;
import com.vdgarcia.appointment_service.model.Status;
import com.vdgarcia.appointment_service.publisher.inter.CreateEventPublisher;
import com.vdgarcia.appointment_service.repository.AppointmentRepository;
import com.vdgarcia.events.AppointmentCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository repository;
    private final PatientClient patientClient;
    private final DoctorClient doctorClient;
    private final CreateEventPublisher publisher;

    @Override
    public AppointmentDTO crear(AppointmentDTO dto) {
        PatientDTO patientDTO = patientClient.obtenerPaciente(dto.getPatientId());
        DoctorDTO doctorDTO = doctorClient.obtenerDoctor(dto.getDoctorId());
        if(patientDTO==null) {
            throw new IllegalArgumentException("Patient Not Founded");
        }
        if(doctorDTO==null){
            throw new IllegalArgumentException("Doctor Not Founded");
        }
        if(doctorDTO.getAvailable().equals(false)){
            throw new IllegalArgumentException("Doctor is not Available for the momento");
        }
        Appointment appointment = Mapper.toAppointment(dto);
        appointment.setStatus(Status.SCHEDULED);
        Appointment creada = repository.save(appointment);

        AppointmentCreated evento = AppointmentCreated.builder()
                .appointmentId(creada.getId())
                .patientId(creada.getPatientId())
                .doctorId(creada.getDoctorId())
                .amount(BigDecimal.valueOf(800.0))
                .date(creada.getDate())
                .build();

        publisher.publishAppointmentCreated(evento);
        return Mapper.toAppointmentDTO(creada);
    }

    @Override
    public void cambiarEstado(Long id) {
        Appointment cita = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Appointment Not Founded")
        );
        cita.setStatus(Status.COMPLETED);
        repository.save(cita);
    }
}
