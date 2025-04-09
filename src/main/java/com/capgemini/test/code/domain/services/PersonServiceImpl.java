package com.capgemini.test.code.domain.services;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capgemini.test.code.clients.CheckDniRequest;
import com.capgemini.test.code.clients.CheckDniResponse;
import com.capgemini.test.code.clients.DniClient;
import com.capgemini.test.code.domain.contracts.repositories.PersonRepository;
import com.capgemini.test.code.domain.contracts.services.PersonService;
import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.InvalidDataException;
import com.capgemini.test.code.exceptions.NotFoundException;

import feign.FeignException;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository repo;
    private DniClient dniClient;

    public PersonServiceImpl(PersonRepository repo, DniClient dniClient) {
        this.repo = repo;
        this.dniClient = dniClient;
    }

    @Override
    public List<Person> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Person> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public Person add(Person item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("Item cannot be null");
        }
        if (!isDniValid(item.getDni())) {
            Map<String, String> errors = Map.of("dni", "DNI is not valid");
            throw new InvalidDataException("DNI is not valid", errors);
        }
        if (item.isInvalid()) {
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        }
        // VALIDAR DNI CONTRA API

        var aux = repo.insert(item);
        sendNotification(item);
        return aux;
    }

    @Override
    public Person modify(Person item) throws NotFoundException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("Item cannot be null");
        }
        if (!isDniValid(item.getDni())) {
            Map<String, String> errors = Map.of("dni", "DNI is not valid");
            throw new InvalidDataException("DNI is not valid", errors);
        }
        if (item.isInvalid()) {
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        }
        // VALIDAR DNI CONTRA API

        var aux = repo.update(item);
        sendNotification(item);
        return aux;
    }

    @Override
    public void delete(Person item) throws InvalidDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    private void sendNotification(Person newItem) {
        try {
            URL url = new URL(newItem.getNotification().getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = newItem.getNotification().getBody();

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            System.out.println("For petition: " + newItem.getNotification().getUrl());
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isDniValid(String dni) {
        CheckDniRequest request = new CheckDniRequest(dni);

        try {
            ResponseEntity<CheckDniResponse> response = dniClient.check(request);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("DNI válido");
                return true;
            }
        } catch (FeignException.Conflict e) {
            // Esto captura el HTTP 409
            System.out.println("DNI inválido: " + e.getMessage());
            return false;
        }
        return false;

    }
}
