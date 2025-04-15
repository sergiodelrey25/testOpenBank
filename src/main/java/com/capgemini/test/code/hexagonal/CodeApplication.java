package com.capgemini.test.code.hexagonal;

import java.util.TreeMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import org.springdoc.core.customizers.OpenApiCustomizer;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "OpenBank: Test", version = "1.0", description = "Prueba técnica para OpenBank", contact = @Contact(name = "Sergio del Rey", url = "https://github.com/sergiodelrey25", email = "sergiodelrey25@gmail.com")), externalDocs = @ExternalDocumentation(description = "Documentación del proyecto", url = "https://github.com/capCCA/capgemini-test"))
public class CodeApplication {

  @Bean
  public OpenApiCustomizer sortSchemasAlphabetically() {
    return openApi -> {
      var schemas = openApi.getComponents().getSchemas();
      if (schemas != null) {
        openApi.getComponents().setSchemas(new TreeMap<>(schemas));
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(CodeApplication.class, args);
  }
}
