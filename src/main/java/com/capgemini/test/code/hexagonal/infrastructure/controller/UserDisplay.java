package com.capgemini.test.code.hexagonal.infrastructure.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDisplay {
    @Schema(description = "ID del usuario", example = "USER-001")
    private Long id;
}
