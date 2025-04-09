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
}
