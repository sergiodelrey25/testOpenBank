package com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity extends AbstractEntity<RoomEntity> {
    private String name;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users = new ArrayList<>();

    public void addUser(UserEntity user) {
        this.users.add(user);
        user.setRoom(this);
    }

    public void removeUser(UserEntity user) {
        this.users.remove(user);
        user.setRoom(null);
    }
}
