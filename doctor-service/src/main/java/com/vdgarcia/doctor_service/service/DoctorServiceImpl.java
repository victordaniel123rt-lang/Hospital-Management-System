package com.vdgarcia.doctor_service.service;

import com.vdgarcia.doctor_service.dto.DoctorDTO;
import com.vdgarcia.doctor_service.mapper.Mapper;
import com.vdgarcia.doctor_service.model.Doctor;
import com.vdgarcia.doctor_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository repository;

    @Override
    public List<DoctorDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toDoctorDTO).toList();
    }

    @Override
    public DoctorDTO obtenerPorId(Long id) {
        Doctor doctor = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Doctor Not Founded")
        );
        return Mapper.toDoctorDTO(doctor);
    }

    @Override
    public DoctorDTO crear(DoctorDTO doctorDTO) {
        Doctor doctor = Mapper.toDoctor(doctorDTO);
        Doctor creado = repository.save(doctor);
        return Mapper.toDoctorDTO(creado);
    }

    @Override
    public DoctorDTO actualizar(Long id, DoctorDTO doctorDTO) {
        Doctor doctor = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Doctor Not Founded")
        );
        Mapper.updateDoctor(doctorDTO,doctor);
        Doctor actualizado = repository.save(doctor);
        return Mapper.toDoctorDTO(actualizado);
    }

    @Override
    public DoctorDTO eliminar(Long id) {
        Doctor doctor = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Doctor Not Founded")
        );
        repository.delete(doctor);
        return Mapper.toDoctorDTO(doctor);
    }
}
