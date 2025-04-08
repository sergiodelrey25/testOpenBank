package com.capgemini.test.code.domain.entities.roles;

import com.capgemini.test.code.domain.entities.Person;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class Admin implements Role {

    private final String name = "USER";
    // Simulación de un correo electrónico

    @Override
    public String getNotification(Person to) {
        return """
                From: System
                To: """ + to.getEmail() + """
                \tSubject: Notification
                \tMessage: Usuario guardado.
                \tPlease check your inbox for more details.""";
    }

    @Override
    public void sendNotification(Person to) {
        // Hacer un POST a http//localhost:1080/email
        try {
            URL url = new URL("http://localhost:1080/email");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String email = to.getEmail();
            String message = "Usuario guardado.";
            String jsonInputString = String.format("{\"email\": \"%s\", \"message\": \"%s\"}", email, message);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

}
