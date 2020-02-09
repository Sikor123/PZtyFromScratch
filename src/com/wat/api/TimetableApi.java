package com.wat.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.DataProvider;
import com.wat.dto.EventDto;
import com.wat.dto.TimetableDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TimetableApi {

    private String basePath = "http://localhost:8080/api/timetable/";

    private HttpClient client;
    private ObjectMapper mapper;

    public TimetableApi() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public TimetableDto getTimetable(Long eventId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + eventId))
                .header("Authorization", "Bearer " + DataProvider.jwtTokenDto.getToken())
                .GET()
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(httpResponse.body(), TimetableDto.class);
    }

    public void createTimetable(Long eventId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + eventId))
                .header("Authorization", "Bearer " + DataProvider.jwtTokenDto.getToken())
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void deleteTimetable(Long eventId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(basePath + eventId))
                .header("Authorization", "Bearer " + DataProvider.jwtTokenDto.getToken())
                .DELETE()
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
