package com.vdgarcia.patient_service.mapper;

import com.vdgarcia.patient_service.dto.PatientDTO;
import com.vdgarcia.patient_service.model.Patient;

public class Mapper {

    public static PatientDTO toPatientDTO(Patient entity){
        if (entity==null) return null;

        return PatientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .birthDate(entity.getBirthDate())
                .build();
    }


    public static Patient toPatient(PatientDTO dto){
        if(dto==null) return null;
        return Patient.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .build();
    }



    public static void updatePatiente(PatientDTO dto, Patient entity){
        if (dto==null || entity == null) return;

        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setBirthDate(dto.getBirthDate());
    }


}
