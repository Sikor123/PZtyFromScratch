package com.wat.view;

import com.wat.DataProvider;
import com.wat.api.EventsApi;
import com.wat.dto.EventDto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventsView {
    private Stage stage;
    private EventsApi eventsApi;

    public EventsView(Stage stage) throws IOException, InterruptedException {
        this.stage = stage;
        eventsApi = new EventsApi();

        if (DataProvider.jwtTokenDto.getIsAdmin()) {
            DataProvider.events = eventsApi.getAllEvents();

        }else{
            DataProvider.events = eventsApi.getUserEvents(DataProvider.jwtTokenDto.getUsername());
        }
        HBox hBox = new HBox();
        List<VBox> eventComponents = new ArrayList<>();
        DataProvider.events.forEach(event -> createEventComponent(event, eventComponents));
        System.out.println(DataProvider.events);
        hBox.getChildren().addAll(eventComponents);
        Scene scene = new Scene(hBox, 300, 300);
        stage.setScene(scene);

        stage.show();
    }

    private void createEventComponent(EventDto event, List<VBox> eventComponents) {
        TextField eventName = new TextField(event.getName());
        TextField date = new TextField(event.getStartDateTime());
        Button goToEventButton = new Button("Go!");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(eventName, date, goToEventButton);
        eventComponents.add(vBox);
        goToEventButton.setOnAction(e -> goToEventTimetableView(event));
    }

    private void goToEventTimetableView(EventDto event) {
        TimetableView timetableView = new TimetableView(stage, event);
    }


}
