package com.capgemini.test.code.hexagonal.infrastructure.db.postgres.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity.RoomEntity;
import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity.UserEntity;

public interface RoomJpaRepository extends JpaRepository<RoomEntity, Long> {
    // Buscar Usuario por su id dentro del id de la sala
    @Query("SELECT u FROM RoomEntity r JOIN r.users u WHERE r.id = :roomId AND u.id = :userId")
    Optional<UserEntity> findUserInRoom(@Param("roomId") Long roomId, @Param("userId") Long userId);

}
