package com.vdgarcia.appointment_service.repository;

import com.vdgarcia.appointment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
}
