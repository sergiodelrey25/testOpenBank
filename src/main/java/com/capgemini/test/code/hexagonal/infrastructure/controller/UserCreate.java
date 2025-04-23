package com.capgemini.test.code.hexagonal.infrastructure.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreate {
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
}
