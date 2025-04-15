package com.capgemini.test.code.hexagonal.usecase.find;

import java.util.Optional;

public interface IFindUserInRoomInteractor {
    Optional<FindUserOutput> findUserInRoom(Long roomId, Long userId);

}
