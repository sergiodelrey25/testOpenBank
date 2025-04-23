package com.capgemini.test.code.hexagonal.usecase.create;

import com.capgemini.test.code.hexagonal.exceptions.InvalidDataException;

public interface ICreateUserInRoomInteractor {
    Long createUserInRoom(Long roomId, CreateUserInput user) throws InvalidDataException;
}
