package com.capgemini.test.code.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.capgemini.test.code.domain.contracts.repositories.RoomRepository;
import com.capgemini.test.code.domain.contracts.services.RoomService;
import com.capgemini.test.code.domain.entities.Room;
import com.capgemini.test.code.domain.services.RoomServiceImpl;

public class RoomServiceTests {

    private static RoomService srv;
    private static RoomRepository repo;
    private static Room r = new Room("Room 1");

    @BeforeEach
    public void setUp() {
        mockRepository();
        srv = new RoomServiceImpl(repo);
    }

    private static void mockRepository() {
        repo = mock(RoomRepository.class);
        when(repo.findById(1L)).thenReturn(java.util.Optional.of(r));
        when(repo.findById(2L)).thenReturn(java.util.Optional.empty());
    }

    @Nested
    @DisplayName("Get one tests")
    class GetOneTests {
        @Test
        @DisplayName("Get one room")
        public void getOneExists() {
            var item = srv.getOne(1L);
            assertTrue(item.isPresent());
            assertEquals(r, item.get());
        }

        @Test
        @DisplayName("Get one non existing room")
        public void getOneNotExists() {
            var item = srv.getOne(2L);
            assertTrue(item.isEmpty());
        }

    }
}
