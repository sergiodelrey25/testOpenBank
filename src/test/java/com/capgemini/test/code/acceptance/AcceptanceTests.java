package com.capgemini.test.code.acceptance;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.capgemini.test.code.infrastructure.models.UserCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/db/reset-db.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AcceptanceTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Nested
    @DisplayName("Save user in room tests")
    class SaveUserInRoomTests {
        @SuppressWarnings("null")
        @Test
        @DisplayName("Save user in room")
        void testSaveUserInRoom() {
            UserCreateDTO request = new UserCreateDTO("John", "email@email.com",
                    "677998899", "admin", "23454234W");
            ResponseEntity<Map> response = testRestTemplate.postForEntity("/api/users/1", request, Map.class);
            assertTrue(response.getStatusCode().is2xxSuccessful());
            assertTrue(response.getBody().get("id") != null);
        }

        @SuppressWarnings("null")
        @Test
        @DisplayName("Save invalid user in room")
        void testSaveInvalidUserInRoom() {
            UserCreateDTO request = new UserCreateDTO("John", "email@email.com",
                    "677998899", "admin", "99999999w");
            ResponseEntity<Map> response = testRestTemplate.postForEntity("/api/users/1", request, Map.class);
            assertTrue(response.getStatusCode().is4xxClientError());
            assertTrue(response.getBody().get("message").toString().contains("error validation"));
        }

        @SuppressWarnings("null")
        @Test
        @DisplayName("Save invalid user in a room that does not exist")
        void testSaveUserInInvalidRoom() {
            UserCreateDTO request = new UserCreateDTO("John", "email@email.com",
                    "677998899", "admin", "23454234W");
            ResponseEntity<Map> response = testRestTemplate.postForEntity("/api/users/12", request, Map.class);
            assertTrue(response.getStatusCode().is4xxClientError());
            assertTrue(response.getBody().get("message").toString().contains("Room not found"));
        }

    }

    @Nested
    @DisplayName("Get user in room tests")
    class GetUserTests {
        @Test
        @DisplayName("Get user in room")
        void testGetUserInRoom() {
            ResponseEntity<Map> response = testRestTemplate.getForEntity("/api/users/1/1", Map.class);
            assertTrue(response.getStatusCode().is2xxSuccessful());
        }

        @SuppressWarnings("null")
        @Test
        @DisplayName("Get user in room that does not exist")
        void testGetUserInInvalidRoom() {
            ResponseEntity<Map> response = testRestTemplate.getForEntity("/api/users/12/1", Map.class);
            assertTrue(response.getStatusCode().is4xxClientError());
            assertTrue(
                    response.getBody().get("message").toString().contains("Room not found"));
        }

        @SuppressWarnings("null")
        @Test
        @DisplayName("Get user that does not exist in room")
        void testGetInvalidUserInRoom() {
            ResponseEntity<Map> response = testRestTemplate.getForEntity("/api/users/1/12", Map.class);
            assertTrue(response.getStatusCode().is4xxClientError());
            assertTrue(response.getBody().get("message").toString().contains("User not found"));
        }

        @SuppressWarnings("null")
        @Test
        @DisplayName("Get existing user but does not belong to an existing room")
        void testGetUserInRoomThatDoesNotExist() {
            ResponseEntity<Map> response = testRestTemplate.getForEntity("/api/users/1/2", Map.class);
            assertTrue(response.getStatusCode().is4xxClientError());
            assertTrue(response.getBody().get("message").toString().contains("User with id 2 was not found in room 1"));
        }
    }

}
