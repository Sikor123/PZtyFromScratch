package com.wat;

import com.wat.api.AuthenticationClient;
import com.wat.dto.CreateUserDto;
import com.wat.view.LoginView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    private Stage window;
    private LoginView loginView;

    public static void main(String[] args) throws IOException, InterruptedException {
//        AuthenticationClient authenticationClient = new AuthenticationClient();
//        authenticationClient.registerUser(CreateUserDto.builder()
//                .email("email@wp.pl")
//                .firstName("Barti")
//                .lastName("Sikora")
//                .password("admin1")
//                .phoneNumber("+48795594857")
//                .role("ROLE_USER")
//                .build());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("Title");
        loginView = new LoginView(window);

        window.setScene(loginView.getScene());
        window.show();

    }
}
