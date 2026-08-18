package com.vdgarcia.doctor_service.repository;

import com.vdgarcia.doctor_service.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
}
