package com.wat.view;

import com.wat.DataProvider;
import com.wat.api.AuthenticationClient;
import com.wat.dto.JwtTokenDto;
import com.wat.dto.LoginDto;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;

@Data
public class LoginView {
    GridPane grid;
    Label nameLabel;
    TextField nameInput;
    Label passLabel;
    TextField passInput;
    Button loginButton;
    Button registerButton;

    Stage stage;
    AuthenticationClient authenticationClient;

    public LoginView(Stage stage) {

        this.authenticationClient = new AuthenticationClient();
        this.stage = stage;
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        nameInput = new TextField("bartek@wp.pl");
        GridPane.setConstraints(nameInput, 1, 0);

        //Password Label
        passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        passInput = new TextField("admin");
//        passInput.setPromptText();
        GridPane.setConstraints(passInput, 1, 1);

        //Login
        loginButton = new Button("Log In");
        loginButton.setOnAction(e -> {
            try {
                loginUser();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        GridPane.setConstraints(loginButton, 1, 2);

        //Register
        registerButton = new Button("Sign in");
        GridPane.setConstraints(registerButton, 1, 3);
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, registerButton);
        this.grid = grid;
    }

    public Scene getScene(){
        return new Scene(grid, 800, 600);
    }

    private void loginUser() throws IOException, InterruptedException {
        LoginDto loginDto = LoginDto.builder()
                .username(nameInput.getText())
                .password(passInput.getText())
                .build();
        JwtTokenDto jwtTokenDto = authenticationClient.loginUser(loginDto);
        DataProvider.jwtTokenDto = jwtTokenDto;
        EventsView eventsView = new EventsView(stage);

    }


}
