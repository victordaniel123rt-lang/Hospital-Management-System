package com.vdgarcia.doctor_service.mapper;

import com.vdgarcia.doctor_service.dto.DoctorDTO;
import com.vdgarcia.doctor_service.model.Doctor;

public class Mapper {

    public static DoctorDTO toDoctorDTO(Doctor entity){
        if (entity==null) return null;

        return DoctorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .licenseNumber(entity.getLicenseNumber())
                .speciality(entity.getSpeciality())
                .available(entity.getAvailable())
                .build();

    }

    public static Doctor toDoctor(DoctorDTO dto){
        if (dto==null) return null;

        return Doctor.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .speciality(dto.getSpeciality())
                .licenseNumber(dto.getLicenseNumber())
                .available(dto.getAvailable())
                .build();
    }

    public static void updateDoctor(DoctorDTO dto, Doctor entity){
        if (dto==null || entity==null) return;

        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setSpeciality(dto.getSpeciality());
        entity.setAvailable(dto.getAvailable());
        entity.setLicenseNumber(dto.getLicenseNumber());
    }



}
