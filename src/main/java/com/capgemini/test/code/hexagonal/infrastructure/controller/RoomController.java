package com.capgemini.test.code.hexagonal.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.test.code.exceptions.NotFoundException;
import com.capgemini.test.code.hexagonal.exceptions.InvalidDataException;
import com.capgemini.test.code.hexagonal.usecase.create.ICreateUserInRoomInteractor;
import com.capgemini.test.code.hexagonal.usecase.find.IFindUserInRoomInteractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/hexagonal/")
@Tag(name = "room-service", description = "Room management")
@RequiredArgsConstructor
public class RoomController {
    private final IFindUserInRoomInteractor findUserInRoomInteractor;
    private final ICreateUserInRoomInteractor createUserInRoomInteractor;

    @GetMapping(path = "room/{roomId}/user/{userId}")
    @Operation(summary = "Obtiene un usuario en una sala a partir de su id")
    public UserDetails getUserInRoom(@PathVariable Long roomId, @PathVariable Long userId) throws NotFoundException {
        var user = findUserInRoomInteractor.findUserInRoom(roomId, userId);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return UserMapper.toUserDetails(user.get());

    }

    @PostMapping("room/{roomId}/user")
    public UserDisplay createUserInRoom(@Valid @RequestBody UserCreate item, @PathVariable Long roomId)
            throws InvalidDataException {
        Long generatedId = createUserInRoomInteractor.createUserInRoom(roomId, UserMapper.toCreateUserInput(item));

        return UserMapper.toUserDisplay(generatedId);
    }

    // @PostMapping("{roomId}")
    // @ApiResponse(responseCode = "201", description = "Usuario creado")
    // @Operation(summary = "Crea un usuario en una sala")
    // public ResponseEntity<Object> createInRoom(@Valid @RequestBody UserCreateDTO
    // item, @PathVariable Long roomId)
    // throws BadRequestException, DuplicateKeyException, InvalidDataException,
    // NotFoundException {

    // var room = roomSrv.getOne(roomId);
    // if (room.isEmpty()) {
    // throw new NotFoundException("Room not found");
    // }
    // var newItem = srv.add(UserCreateDTO.from(item));
    // newItem.setRoom(room.get());
    // srv.modify(newItem); // Actualiza el usuario con la sala
    // URI location = ServletUriComponentsBuilder.fromCurrentRequest()
    // .path("/{id}")
    // .buildAndExpand(newItem.getId())
    // .toUri();

    // // Cuerpo con el ID
    // Map<String, Object> responseBody = Map.of("id", newItem.getId());

    // return ResponseEntity
    // .created(location) // HTTP 201 con header Location
    // .body(responseBody);
    // }

}
