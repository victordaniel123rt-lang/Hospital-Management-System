package com.vdgarcia.patient_service.unit;

import com.vdgarcia.patient_service.dto.PatientDTO;
import com.vdgarcia.patient_service.model.Patient;
import com.vdgarcia.patient_service.repository.PatientRepository;
import com.vdgarcia.patient_service.service.PatientServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository repository;
    @InjectMocks
    private PatientServiceImpl service;

    @Test
    void testFindById_Founded(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        Patient paciente = new Patient(1L,"Victor","Garcia","vd@example.com","7226435648", birthDate);
        when(this.repository.findById(1L)).thenReturn(Optional.of(paciente));
        PatientDTO dto = this.service.obtenerPorId(1L);
        assertNotNull(dto);
        assertEquals("Victor",dto.getName());
        assertEquals("Garcia",dto.getLastName());
        assertEquals("7226435648",dto.getPhone());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void tesFindById_NotFounded(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()-> this.service.obtenerPorId(1L));
        assertEquals("Patient Not Founded", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }

    @Test
    void testFindAll(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        List<Patient> lista = List.of(
                new Patient(1L,"Victor","Garcia","vd@example.com","7226435648", birthDate),
                new Patient(2L,"Daniel","Garcia","vd@example.com","7226435648", birthDate)
        );
        when(this.repository.findAll()).thenReturn(lista);
        List<PatientDTO> pacientes = this.service.obtenerTodos();
        assertNotNull(pacientes);
        assertEquals(2,pacientes.size());
        verify(this.repository,times(1)).findAll();
    }


    @Test
    void testCrear(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        Patient paciente = new Patient(1L,"Victor","Garcia","vd@example.com","7226435648", birthDate);
        PatientDTO dto = new PatientDTO(null,"Victor","Garcia","vd@example.com","7226435648", birthDate);
        when(this.repository.save(any(Patient.class))).thenReturn(paciente);
        PatientDTO creado = this.service.crear(dto);
        assertNotNull(creado);
        assertEquals("Victor",creado.getName());
        assertEquals("Garcia", creado.getLastName());
        assertTrue(creado.getId()>0);
        verify(this.repository,times(1)).save(any(Patient.class));
    }


    @Test
    void testActualizar_Founded(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        Patient paciente = new Patient(1L,"Victor","Garcia","vd@example.com","7226435648", birthDate);
        PatientDTO dto = new PatientDTO(1L,"Daniel","Garcia","vd@example.com","7226435648", birthDate);
        when(this.repository.findById(1L)).thenReturn(Optional.of(paciente));
        when(this.repository.save(any(Patient.class))).thenReturn(paciente);
        PatientDTO actualizado = this.service.actualizar(1L,dto);
        assertNotNull(actualizado);
        assertEquals("Daniel",actualizado.getName());
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).save(any(Patient.class));
    }


    @Test
    void testActualizar_NotFounded(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->this.service.obtenerPorId(1L));
        assertEquals("Patient Not Founded", exception.getMessage());
        verify(this.repository,times(1)).findById(1L);
    }


    @Test
    void testEliminar_Founded(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate birthDate= LocalDate.parse("19-09-00",formato);
        Patient paciente = new Patient(1L,"Victor","Garcia","vd@example.com","7226435648", birthDate);
        when(this.repository.findById(1L)).thenReturn(Optional.of(paciente));
        PatientDTO dto = this.service.eliminar(1L);
        assertNotNull(dto);
        assertEquals("Victor",dto.getName());
        verify(this.repository,times(1)).findById(1L);
        verify(this.repository,times(1)).delete(any(Patient.class));
    }


    @Test
    void testEliminar_NotFounded(){
        when(this.repository.findById(1L)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->this.service.eliminar(1L));
        assertEquals("Patient Not Founded", exception.getMessage());
    }

}
