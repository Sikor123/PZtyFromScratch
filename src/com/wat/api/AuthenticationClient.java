package com.wat.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.dto.CreateUserDto;
import com.wat.dto.JwtTokenDto;
import com.wat.dto.LoginDto;
import com.wat.dto.ResponseMessage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthenticationClient {
    private String basePath = "http://localhost:8080/api/";

    private HttpClient client;
    private ObjectMapper mapper;


    public AuthenticationClient() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }


    public ResponseMessage registerUser(CreateUserDto createUserDto) throws IOException, InterruptedException {
        String json = "";
        try {
            json = mapper.writeValueAsString(createUserDto);
            System.out.println("ResultingJSONstring = " + json);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + "signup"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        ResponseMessage ma = mapper.readValue(httpResponse.body(), ResponseMessage.class);
        return null;
    }

    public JwtTokenDto loginUser(LoginDto loginDto) throws IOException, InterruptedException {
        String json = "";
        try {
            json = mapper.writeValueAsString(loginDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + "login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        JwtTokenDto jwtToken = mapper.readValue(httpResponse.body(), JwtTokenDto.class);
        return jwtToken;
    }

}
