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
        void testSaveUserInRoom() {
            UserCreateDTO request = new UserCreateDTO("John", "email@email.com",
                    "677998899", "admin", "23454234W");
            ResponseEntity<Map> response = testRestTemplate.postForEntity("/api/users/1", request, Map.class);
            assertTrue(response.getStatusCode().is2xxSuccessful());
            assertTrue(response.getBody().get("id") != null);
        }
    }

    @Nested
    @DisplayName("Get user tests")
    class GetUserTests {
        @Test
        void testGetUser() {
            // Implement the test logic here
        }
    }

}
