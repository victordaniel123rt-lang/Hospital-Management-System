package com.vdgarcia.doctor_service.integra;

import com.vdgarcia.doctor_service.dto.DoctorDTO;
import com.vdgarcia.doctor_service.service.DoctorService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class DoctorServiceTest {
    @Autowired
    private DoctorService service;

    @Test
    @Transactional
    void testFindById(){
        Long id = 1L;
        DoctorDTO doctor = this.service.obtenerPorId(id);
        assertNotNull(doctor);
        assertEquals("Roberto", doctor.getName());
        assertEquals("MED-12345", doctor.getLicenseNumber());
        assertEquals("Cardiología", doctor.getSpeciality());
    }

    @Test
    @Transactional
    void testFindById_NotFounded(){
        Long id = 9L;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> this.service.obtenerPorId(id));
        assertEquals("Doctor Not Founded", exception.getMessage());
    }

    @Test
    @Transactional
    void testFindAll(){
        List<DoctorDTO> doctores = this.service.obtenerTodos();
        assertNotNull(doctores);
        assertEquals(8,doctores.size());

    }


    @Test
    @Transactional
    void testCrear(){
        DoctorDTO doctor = new DoctorDTO(null, "Carlos", "Ramírez", "Cardiología", "MED-12345", true);
        DoctorDTO dto = this.service.crear(doctor);
        assertNotNull(dto);
        assertTrue(dto.getId()>0);
        assertEquals("Carlos", dto.getName());
        assertEquals("Ramírez", dto.getLastName());
    }

    @Test
    @Transactional
    void testActualizar_Founded(){
        Long id = 1L;
        DoctorDTO doctor = new DoctorDTO(1L, "Carlos", "Ramírez", "Cardiología", "MED-12345", false);
        DoctorDTO dto = this.service.actualizar(1L, doctor);
        assertNotNull(dto);
        assertEquals("Carlos", dto.getName());
        assertEquals(false,dto.getAvailable());
    }

    @Test
    @Transactional
    void testActualizar_NotFounded(){
        Long id = 9L;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> this.service.actualizar(id,new DoctorDTO()));
        assertEquals("Doctor Not Founded", exception.getMessage());
    }
    
    @Test
    @Transactional
    void testEliminar(){
        DoctorDTO dto = this.service.eliminar(1L);
        assertNotNull(dto);
        assertEquals("Roberto", dto.getName());
        assertEquals("MED-12345", dto.getLicenseNumber());
    }

    @Test
    @Transactional
    void testEliminar_NotFounded(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> this.service.eliminar(9L));
        assertEquals("Doctor Not Founded", exception.getMessage());
    }

    
    














}
