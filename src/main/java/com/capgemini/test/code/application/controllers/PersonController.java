package com.capgemini.test.code.application.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.test.code.domain.contracts.services.PersonService;
import com.capgemini.test.code.domain.entities.models.UserCreateDTO;
import com.capgemini.test.code.domain.entities.models.UserDisplayDTO;
import com.capgemini.test.code.exceptions.BadRequestException;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.InvalidDataException;
import com.capgemini.test.code.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "user-service", description = "Gestión de usuarios")
public class PersonController {
    private PersonService srv;

    public PersonController(PersonService srv) {
        this.srv = srv;
    }

    @GetMapping(path = "/v1/{id}")
    @Operation(summary = "Obtiene un usuario a partir de su id")
    public UserDisplayDTO getOne(@PathVariable Long id) throws NotFoundException {
        var item = srv.getOne(id);
        if (item.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return UserDisplayDTO.from(item.get());
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @Operation(summary = "Crea un usuario")
    public ResponseEntity<Object> create(@Valid @RequestBody UserCreateDTO item)
            throws BadRequestException, DuplicateKeyException, InvalidDataException {
        var newItem = srv.add(UserCreateDTO.from(item));
        newItem.sendNotification();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
