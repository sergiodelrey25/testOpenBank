package com.capgemini.test.code.hexagonal.usecase.create;

public interface ICreateUserInRoomInteractor {
    void createUserInRoom(String roomId, CreateUserInput user);
}
