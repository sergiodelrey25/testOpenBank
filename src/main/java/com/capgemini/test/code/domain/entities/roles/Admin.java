package com.capgemini.test.code.domain.entities.roles;

import com.capgemini.test.code.domain.entities.Person;

public class Admin implements Role {

    private final String name = "USER";
    private final String url = "http://localhost:1080/email";
    // Simulación de un correo electrónico

    @Override
    public Notification getNotification(Person to) {
        String email = to.getEmail();
        String message = "Usuario guardado.";
        String jsonInputString = String.format("{\"email\": \"%s\", \"message\": \"%s\"}", email, message);
        return new Notification(url, jsonInputString);
    }

    // @Override
    // public void sendNotification(Person to) {
    // // Hacer un POST a http//localhost:1080/email
    // try {
    // URL url = new URL("http://localhost:1080/email");
    // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    // connection.setRequestMethod("POST");
    // connection.setRequestProperty("Content-Type", "application/json");
    // connection.setDoOutput(true);

    // String email = to.getEmail();
    // String message = "Usuario guardado.";
    // String jsonInputString = String.format("{\"email\": \"%s\", \"message\":
    // \"%s\"}", email, message);

    // try (OutputStream os = connection.getOutputStream()) {
    // byte[] input = jsonInputString.getBytes("utf-8");
    // os.write(input, 0, input.length);
    // }

    // int responseCode = connection.getResponseCode();
    // if (responseCode != HttpURLConnection.HTTP_OK) {
    // throw new RuntimeException("Failed : HTTP error code : " + responseCode);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    @Override
    public String getName() {
        return this.name;
    }

}
