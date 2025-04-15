package com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends AbstractEntity<UserEntity> {
    private String name;
    private String email;
    private String dni;
    private String phone;
    private String role;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", foreignKey = @jakarta.persistence.ForeignKey(name = "fk_room"), nullable = true)
    @JsonManagedReference
    private RoomEntity room;

    public void setRoom(RoomEntity roomEntity) {
        this.room = roomEntity;
    }

}
