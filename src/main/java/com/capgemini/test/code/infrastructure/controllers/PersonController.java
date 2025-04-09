package com.capgemini.test.code.infrastructure.controllers;

import java.net.URI;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.test.code.domain.contracts.services.PersonService;
import com.capgemini.test.code.domain.contracts.services.RoomService;
import com.capgemini.test.code.exceptions.BadRequestException;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.InvalidDataException;
import com.capgemini.test.code.exceptions.NotFoundException;
import com.capgemini.test.code.infrastructure.models.UserCreateDTO;
import com.capgemini.test.code.infrastructure.models.UserDisplayDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "user-service", description = "Gestión de usuarios")
public class PersonController {
    private PersonService srv;
    private RoomService roomSrv;

    public PersonController(PersonService srv, RoomService roomSrv) {
        this.srv = srv;
        this.roomSrv = roomSrv;
    }

    @GetMapping(path = "{id}/{roomId}")
    @Operation(summary = "Obtiene un usuario a partir de su id")
    // Dentro de una sala
    public UserDisplayDTO getOne(@PathVariable Long id, @PathVariable Long roomId) throws NotFoundException {
        var item = srv.getOne(id);
        if (item.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return UserDisplayDTO.from(item.get());
    }

    @PostMapping("{roomId}")
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @Operation(summary = "Crea un usuario en una sala")
    public ResponseEntity<Object> createInRoom(@Valid @RequestBody UserCreateDTO item, @PathVariable Long roomId)
            throws BadRequestException, DuplicateKeyException, InvalidDataException, NotFoundException {

        var room = roomSrv.getOne(roomId);
        if (room.isEmpty()) {
            throw new NotFoundException("Room not found");
        }
        var newItem = srv.add(UserCreateDTO.from(item));
        newItem.setRoom(room.get());
        srv.modify(newItem); // Actualiza el usuario con la sala
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newItem.getId())
                .toUri();

        // Cuerpo con el ID
        Map<String, Object> responseBody = Map.of("id", newItem.getId());

        return ResponseEntity
                .created(location) // HTTP 201 con header Location
                .body(responseBody);
    }

}
