package com.colak.springjpamultitenantseparateschematutorial.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonControllerTest {

    // Same as
    // curl --location --request GET 'localhost:8080/person/1' --header 'X-PrivateTenant: tenanta' --header 'Content-Type: application/json'
    @Test
    void testGetPersonTenantA() {
        // Define the URL
        String url = "http://localhost:8080/person/1";

        // Define the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-PrivateTenant", "tenanta");

        // Create the HTTP entity with headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send GET request
        ResponseEntity<PersonDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PersonDto.class);
        PersonDto body = responseEntity.getBody();
        PersonDto expected = new PersonDto("John Doe", 30, "john.doe@example.com");
        assertEquals(expected, body);
    }

    @Test
    void testGetPersonTenantB() {
        // Define the URL
        String url = "http://localhost:8080/person/1";

        // Define the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-PrivateTenant", "tenantb");

        // Create the HTTP entity with headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send GET request
        ResponseEntity<PersonDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, PersonDto.class);
        PersonDto body = responseEntity.getBody();
        PersonDto expected = new PersonDto("Alice Johnson", 28, "alice.johnson@example.com");
        assertEquals(expected, body);
    }
}
