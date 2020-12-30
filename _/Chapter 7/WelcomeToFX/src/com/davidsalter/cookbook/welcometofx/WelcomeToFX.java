/*
 * Copyright 2014 David Salter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.davidsalter.cookbook.welcometofx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author David Salter <david@developinjava.com>
 */
public class WelcomeToFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setPadding(new Insets(20));
        Label helloLabel = new Label("Hello");
        gridPane.add(helloLabel, 0, 0);

        Label nameLabel = new Label("What's your name?");
        gridPane.add(nameLabel, 0, 1);

        TextField nameTextField = new TextField();
        gridPane.add(nameTextField, 1, 1);

        Button helloButton = new Button("Say Hello");
        HBox horizontalBox = new HBox(10);
        horizontalBox.setAlignment(Pos.BOTTOM_RIGHT);
        horizontalBox.getChildren().add(helloButton);
        gridPane.add(horizontalBox, 1, 2);

        helloButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Greetings.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setScene(new Scene((Pane) loader.load()));
                    GreetingsController controller
                            = loader.<GreetingsController>getController();
                    controller.setName(nameTextField.getText());
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(WelcomeToFX.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        });

        Scene scene = new Scene(gridPane, 350, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
