package com.capgemini.test.code.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import com.capgemini.test.code.clients.CheckDniRequest;
import com.capgemini.test.code.clients.CheckDniResponse;
import com.capgemini.test.code.clients.DniClient;
import com.capgemini.test.code.domain.contracts.repositories.PersonRepository;
import com.capgemini.test.code.domain.contracts.services.PersonService;
import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.domain.entities.roles.Admin;
import com.capgemini.test.code.domain.services.PersonServiceImpl;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.NotFoundException;

public class PersonServiceTests {

    private static PersonService service;
    private static PersonRepository repo;
    private static DniClient dniClient;

    private static Person p;

    @BeforeEach
    public void setUp() {
        p = new Person("Nombre", "tengo@y.ole",
                new Admin(), "000000000", "12345678A");
        mockDniClient();
        mockRepository();
        service = new PersonServiceImpl(repo, dniClient);
    }

    private static void mockDniClient() {
        // POR DEFECTO, EL DNI ES VALIDO
        dniClient = Mockito.mock(DniClient.class);
        CheckDniResponse mockResponse = new CheckDniResponse("Valid DNI");
        when(dniClient.check(any(CheckDniRequest.class))).thenReturn(ResponseEntity.status(200).body(mockResponse));
    }

    private static void mockRepository() {
        repo = Mockito.mock(PersonRepository.class);
        when(repo.findById(1L)).thenReturn(Optional.of(p));
        when(repo.findById(2L)).thenReturn(Optional.empty());
        try {
            when(repo.insert(p)).thenReturn(p);
            when(repo.update(p)).thenReturn(p);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

    }

    @Nested
    @DisplayName("Get one tests")
    class GetOneTests {
        @Test
        @DisplayName("Test get one exists")
        public void getOneExists() {
            var item = service.getOne(1L);
            assertTrue(item.isPresent());
            assertEquals(item.get(), p);
        }

        @Test
        @DisplayName("Test get one not exists")
        public void getOneNotExists() {
            var item = service.getOne(2L);
            assertTrue(item.isEmpty());
        }

    }

    @Nested
    @DisplayName("Add tests")
    class AddTests {
        @Test
        @DisplayName("Test add user")
        public void addUser() {
            try {
                var item = service.add(p);
                assertEquals(item, p);
            } catch (Exception e) {
                fail();
            }

        }

        @Test
        @DisplayName("Test add null user")
        public void addNullUser() {
            try {
                service.add(null);
                fail("Expected an exception to be thrown");
            } catch (Exception e) {
                assertEquals("Item cannot be null", e.getMessage());
            }
        }

        @Test
        @DisplayName("Test add user dni invalid")
        public void addUserDniInvalid() {
            // Mockear la respuesta del cliente DNI para que devuelva un error
            CheckDniResponse mockResponse = new CheckDniResponse("Invalid DNI");
            when(dniClient.check(any(CheckDniRequest.class))).thenReturn(ResponseEntity.status(400).body(mockResponse));

            try {
                service.add(p);
                fail("Expected an exception to be thrown");
            } catch (Exception e) {
                assertEquals("DNI is not valid", e.getMessage());
            }
        }

        @Test
        @DisplayName("Test add user invalid")
        public void addUserInvalid() {
            p.setName("NombreDemasiadoLargo");
            try {
                service.add(p);
                fail("Expected an exception to be thrown");
            } catch (Exception e) {
                assertTrue(e.getMessage().contains("ERRORES"));
            }
        }
    }

    @Nested
    @DisplayName("Update tests")
    class UpdateTests {
        @Test
        @DisplayName("Test update user")
        public void updateUser() {
            try {
                var item = service.modify(p);
                assertEquals(item, p);
            } catch (Exception e) {
                fail();
            }
        }

        @Test
        @DisplayName("Test update null user")
        public void addNullUser() {
            try {
                service.modify(null);
                fail("Expected an exception to be thrown");
            } catch (Exception e) {
                assertEquals("Item cannot be null", e.getMessage());
            }
        }

        @Test
        @DisplayName("Test update user dni invalid")
        public void updateUserDniInvalid() {
            // Mockear la respuesta del cliente DNI para que devuelva un error
            CheckDniResponse mockResponse = new CheckDniResponse("Invalid DNI");
            when(dniClient.check(any(CheckDniRequest.class))).thenReturn(ResponseEntity.status(400).body(mockResponse));

            try {
                service.modify(p);
                fail("Expected an exception to be thrown");
            } catch (Exception e) {
                assertEquals("DNI is not valid", e.getMessage());
            }
        }

        @Test
        @DisplayName("Test update user invalid")
        public void addUserInvalid() {
            p.setName("NombreDemasiadoLargo");
            try {
                service.modify(p);
                fail("Expected an exception to be thrown");
            } catch (Exception e) {
                assertTrue(e.getMessage().contains("ERRORES"));
            }
        }
    }
}
