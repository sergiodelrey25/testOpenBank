package com.capgemini.test.code.domain.entities.roles;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.capgemini.test.code.domain.entities.Person;

public class SuperAdmin implements Role {
    private final String name = "ADMIN";
    // Simulación de un SMS

    @Override
    public String getNotification(Person to) {
        return to.getName() + """
                 you have a NEW Message from System:
                Usuario guardado.
                """;
    }

    @Override
    public void sendNotification(Person to) {
        // Hacer un POST a http//localhost:1080/email
        try {
            URL url = new URL("http://localhost:1080/sms");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String phone = to.getPhone();
            String message = "Usuario guardado.";
            String jsonInputString = String.format("{\"phone\": \"%s\", \"message\": \"%s\"}", phone, message);

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
