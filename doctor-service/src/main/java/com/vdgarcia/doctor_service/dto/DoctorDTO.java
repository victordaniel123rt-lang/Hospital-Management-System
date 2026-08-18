package com.vdgarcia.doctor_service.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String lastName;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 3, max = 50, message = "La especialidad debe tener entre 3 y 50 caracteres")
    private String speciality;

    @NotBlank(message = "La cédula/licencia médica es obligatoria")
    @Pattern(regexp = "^[A-Za-z0-9-]{5,20}$", message = "La licencia debe ser alfanumérica y tener entre 5 y 20 caracteres")
    private String licenseNumber;

    @NotNull(message = "El estado de disponibilidad es obligatorio")
    private Boolean available;
}
