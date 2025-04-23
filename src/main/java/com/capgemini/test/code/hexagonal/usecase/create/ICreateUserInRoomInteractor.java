package com.capgemini.test.code.hexagonal.usecase.create;

public interface ICreateUserInRoomInteractor {
    Long createUserInRoom(Long roomId, CreateUserInput user);
}
