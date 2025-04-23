package com.capgemini.test.code.hexagonal.infrastructure.clients.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.capgemini.test.code.hexagonal.domain.rest.DniClientPort;
import com.capgemini.test.code.hexagonal.infrastructure.clients.CheckDniRequest;
import com.capgemini.test.code.hexagonal.infrastructure.clients.CheckDniResponse;
import com.capgemini.test.code.hexagonal.infrastructure.clients.DniClient;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DniClientAdapter implements DniClientPort {
    private final DniClient dniClient;

    @Override
    public boolean check(String dni) {
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
