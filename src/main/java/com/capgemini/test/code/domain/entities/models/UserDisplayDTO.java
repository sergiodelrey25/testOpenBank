package com.capgemini.test.code.domain.entities.models;

import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.domain.entities.roles.Role;
import com.capgemini.test.code.domain.entities.roles.RoleConverter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Convert;
import jakarta.validation.constraints.Pattern;

public class UserDisplayDTO {
    @Schema(description = "ID del usuario", example = "USER-001")
    private Long id;
    @Schema(description = "Nombre del usuario", example = "José", required = true)
    private String name;
    @Schema(description = "Dirección de correo del usuario", example = "example@email.com", required = true)
    private String email;
    @Schema(description = "Número de teléfono del usuario", example = "000 00 00 00", required = true)
    private String phone;
    @Schema(description = "Rol del usuario", example = "Admin", required = true)
    private String role;
    @Schema(description = "DNI del usuario", example = "00000001A", required = true)
    private String dni;

    static RoleConverter roleConverter = new RoleConverter();

    public UserDisplayDTO() {
    }

    public UserDisplayDTO(Long id, String name, String email, String phone, String role, String dni) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.dni = dni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public static UserDisplayDTO from(Person person) {
        return new UserDisplayDTO(person.getId(), person.getName(), person.getEmail(), person.getPhone(),
                person.getRole().getName(), person.getDni());
    }

    public static Person from(UserDisplayDTO dto) {
        Role role = roleConverter.convertToEntityAttribute(dto.getRole());

        return new Person(dto.getName(),
                dto.getEmail(),
                role,
                dto.getPhone(),
                dto.getDni());
    }

}
