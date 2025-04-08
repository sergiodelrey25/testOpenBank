package com.capgemini.test.code.domain.entities;

import com.capgemini.test.code.domain.core.entities.AbstractEntity;
import com.capgemini.test.code.domain.entities.roles.Role;
import com.capgemini.test.code.domain.entities.roles.RoleConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Person extends AbstractEntity<Person> {

    // No puede tener más de 6 caracteres
    @Size(max = 6, message = "El nombre no puede ser mayor de 6 caracteres")
    private String name;
    // Debe contener un @ y un .
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El email no es válido")
    private String email;
    private String dni;
    private String phone;
    // Este atributo NO se persiste sin convertirlo a String
    @Convert(converter = RoleConverter.class)
    private Role role;
    // Relación con la clase Room
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", foreignKey = @jakarta.persistence.ForeignKey(name = "fk_room"), nullable = true)
    @JsonManagedReference
    private Room room;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Person() {
        super();
    }

    public Person(String name, String email, Role role, String phone, String dni) {
        super();
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.dni = dni;
        this.id = generateId(); // Ensure id is initialized
    }

    private Long generateId() {
        return System.currentTimeMillis(); // Example id generation logic
    }

    public Person(Long id, String name, String email, Role role, String phone, String dni) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.dni = dni;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void sendNotification() {
        this.role.sendNotification(this);
    }

}
