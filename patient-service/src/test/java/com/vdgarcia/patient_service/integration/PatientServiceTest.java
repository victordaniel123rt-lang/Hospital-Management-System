package com.vdgarcia.patient_service.integration;

import com.vdgarcia.patient_service.dto.PatientDTO;
import com.vdgarcia.patient_service.service.PatientService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class PatientServiceTest {
    @Autowired
    private PatientService service;

    @Test
    @Transactional
    void testFindById_Founded(){
        Long id = 1L;
        PatientDTO dto = this.service.obtenerPorId(id);
        assertNotNull(dto);
        assertEquals("Carlos", dto.getName());
        assertEquals("Ramírez",dto.getLastName());

    }

    @Test
    @Transactional
    void testFindById_NotFounded(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->this.service.obtenerPorId(9L));
        assertEquals("Patient Not Founded", exception.getMessage());
    }

    @Test
    @Transactional
    void testFindALL(){
        List<PatientDTO> dtos = this.service.obtenerTodos();
        assertEquals(8,dtos.size());
        assertNotNull(dtos);
    }

    @Test
    @Transactional
    void testCrear(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        PatientDTO patient = new PatientDTO(null, "Yadira", "Gomez","yadi@example.com","72264351217",birthDate);
        PatientDTO dto = this.service.crear(patient);
        assertEquals("Yadira",dto.getName());
        assertTrue(dto.getId()>0);
        assertEquals("Gomez",dto.getLastName());
    }

    @Test
    @Transactional
    void testActualizar(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        Long id = 3L;
        PatientDTO patient = new PatientDTO(id, "Luis", "Hernández","yadi@example.com","72264351217", birthDate);
        PatientDTO dto = this.service.actualizar(id, patient);
        assertNotNull(dto);
        assertEquals("yadi@example.com",patient.getEmail());
        assertEquals("72264351217",patient.getPhone());
    }


    @Test
    @Transactional
    void testActualizar_NotFounded(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        PatientDTO patient = new PatientDTO(9L, "Luis", "Hernández","yadi@example.com","72264351217", birthDate);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->this.service.actualizar(9L,patient));
        assertEquals("Patient Not Founded", exception.getMessage());
    }


    @Test
    @Transactional
    void testEliminar_Founded(){
        Long id = 7L;
        PatientDTO dto = this.service.eliminar(id);
        assertNotNull(dto);
        assertEquals("Torres", dto.getLastName());
    }


    @Test
    @Transactional
    void testEliminar_NotFounded(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> this.service.eliminar(9L));
        assertEquals("Patient Not Founded", exception.getMessage());
    }

}
