package com.vdgarcia.doctor_service.unit;

import com.vdgarcia.doctor_service.dto.DoctorDTO;
import com.vdgarcia.doctor_service.model.Doctor;
import com.vdgarcia.doctor_service.repository.DoctorRepository;
import com.vdgarcia.doctor_service.service.DoctorServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository repository;
    @InjectMocks
    private DoctorServiceImpl service;

    @Test
    void testFindById_Founded(){
        Doctor doctor = new Doctor(1L, "Carlos", "Ramírez", "Cardiología", "MED-12345", true);
        when(this.repository.findById(1L)).thenReturn(Optional.of(doctor));
        DoctorDTO dto = this.service.obtenerPorId(1L);
        assertNotNull(dto);
        assertEquals("Carlos",dto.getName());
        assertEquals("Ramírez", dto.getLastName());
        assertEquals("Cardiología", dto.getSpeciality());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFounded(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->this.service.obtenerPorId(1L));
        assertEquals("Doctor Not Founded", exception.getMessage());
    }

    @Test
    void testFindAll(){
        List<Doctor> doctores = List.of(
                new Doctor(1L, "Carlos", "Ramírez", "Cardiología", "MED-12345", true),
                new Doctor(1L, "Ana", "Ramírez", "Cardiología", "MED-12345", true)
        );
        when(this.repository.findAll()).thenReturn(doctores);
        List<DoctorDTO> dtos = this.service.obtenerTodos();
        assertNotNull(dtos);
        assertEquals(2, dtos.size());
    }

    @Test
    void testCrear(){
        Doctor doctor = new Doctor(1L, "Carlos", "Ramírez", "Cardiología", "MED-12345", true);
        DoctorDTO doctorDTO = new DoctorDTO(null, "Carlos", "Ramírez", "Cardiología", "MED-12345", true);
        when(this.repository.save(any(Doctor.class))).thenReturn(doctor);
        DoctorDTO dto = this.service.crear(doctorDTO);
        assertNotNull(dto);
        assertEquals("Carlos", dto.getName());
        assertEquals("Ramírez", dto.getLastName());
        assertTrue(dto.getId()>0);
        verify(this.repository,times(1)).save(any(Doctor.class));
    }


    @Test
    void testActualizar(){
        Doctor doctor = new Doctor(1L, "Carlos", "Ramírez", "Cardiología", "MED-12345", true);
        DoctorDTO doctorDTO = new DoctorDTO(1L, "Edgar", "Perez", "Cardiología", "MED-12346", true);
        Doctor doctor2 = new Doctor(1L, "Edgar", "Perez", "Cardiología", "MED-12346", true);
        when(this.repository.findById(1L)).thenReturn(Optional.of(doctor));
        when(this.repository.save(any(Doctor.class))).thenReturn(doctor2);
        DoctorDTO dto = this.service.actualizar(1L,doctorDTO);
        assertNotNull(dto);
        assertEquals("Edgar",dto.getName());
        assertEquals("Perez",dto.getLastName());
        assertEquals("MED-12346",dto.getLicenseNumber());
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).save(any(Doctor.class));
    }

    @Test
    void testActualizar_NotFounded(){
        DoctorDTO doctorDTO = new DoctorDTO(1L, "Edgar", "Perez", "Cardiología", "MED-12346", true);
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()-> this.service.actualizar(1L,doctorDTO));
        assertEquals("Doctor Not Founded", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testEliminar_Founded(){
        Doctor doctor = new Doctor(1L, "Carlos", "Ramírez", "Cardiología", "MED-12345", true);
        when(this.repository.findById(1L)).thenReturn(Optional.of(doctor));
        DoctorDTO doctorDTO = this.service.eliminar(1L);
        assertNotNull(doctorDTO);
        assertEquals("Carlos", doctorDTO.getName());
        assertEquals("Cardiología", doctorDTO.getSpeciality());
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).delete(any(Doctor.class));
    }


    @Test
    void testEliminar_NotFounded(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> this.service.eliminar(1L));
        assertEquals("Doctor Not Founded", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }










}
