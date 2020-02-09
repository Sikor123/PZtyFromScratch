package com.wat.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.DataProvider;
import com.wat.dto.EventDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EventsApi {
    private String basePath = "http://localhost:8080/api/";

    private HttpClient client;
    private ObjectMapper mapper;
    private TypeReference<List<EventDto>> typeReference = new TypeReference<List<EventDto>>() {
    };

    public EventsApi() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public List<EventDto> getAllEvents() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + "events"))
                .header("Authorization", "Bearer " + DataProvider.jwtTokenDto.getToken())
                .GET()
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        List<EventDto> events = mapper.readValue(httpResponse.body(), typeReference);
        return events;
    }

    public List<EventDto> getUserEvents(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + "events/" + username))
                .header("Authorization", "Bearer " + DataProvider.jwtTokenDto.getToken())
                .GET()
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        List<EventDto> events = mapper.readValue(httpResponse.body(), typeReference);
        return events;
    }
}

