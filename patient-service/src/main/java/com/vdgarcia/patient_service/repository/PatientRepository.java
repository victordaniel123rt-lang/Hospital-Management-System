package com.vdgarcia.patient_service.repository;

import com.vdgarcia.patient_service.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long>{
}
