package com.capgemini.test.code.application.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.test.code.domain.contracts.services.PersonService;
import com.capgemini.test.code.domain.contracts.services.RoomService;
import com.capgemini.test.code.exceptions.BadRequestException;
import com.capgemini.test.code.exceptions.InvalidDataException;
import com.capgemini.test.code.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "room-service", description = "Gestión de salas")
public class RoomController {
    private RoomService srv;
    private PersonService userSrv;

    public RoomController(RoomService srv, PersonService userSrv) {
        this.srv = srv;
        this.userSrv = userSrv;
    }

    @PutMapping("/{idRoom}/{idUser}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Añade un usuario a una sala")
    public void addUserToRoom(@PathVariable Long idRoom, @PathVariable Long idUser)
            throws NotFoundException, BadRequestException, InvalidDataException {
        var room = srv.getOne(idRoom);
        if (room.isEmpty()) {
            throw new NotFoundException("Room not found");
        }
        var user = userSrv.getOne(idUser);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        // Check that user is not already in a room
        if (user.get().getRoom() != null) {
            throw new BadRequestException("User already in a room");
        }
        var newRoom = room.get();
        newRoom.addPerson(user.get());
        srv.modify(newRoom);
    }
}
