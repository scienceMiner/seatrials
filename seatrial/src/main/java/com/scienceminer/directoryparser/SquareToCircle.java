package com.scienceminer.directoryparser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SquareToCircle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a pane to hold the shapes
        Pane pane = new Pane();

        // Create a rectangle and set its properties
        Rectangle rectangle = new Rectangle(50, 50, 100, 100);
        rectangle.setFill(Color.BLUE);

        // Add the rectangle to the pane
        pane.getChildren().add(rectangle);

        // Create a pause transition to wait for 10 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(10));

        // Create a translate transition to move the rectangle to the center of the pane and transform it into a circle
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2), rectangle);
        translate.setToX(150);
        translate.setToY(150);
        translate.setOnFinished(event -> {
            // Create a circle and set its properties
            Circle circle = new Circle(50);
            circle.setFill(Color.YELLOW);
            circle.setCenterX(150);
            circle.setCenterY(150);

            // Replace the rectangle with the circle in the pane
            pane.getChildren().remove(rectangle);
            pane.getChildren().add(circle);
        });

        // Play the transitions in sequence
        pause.setOnFinished(event -> translate.play());
        pause.play();

        // Create a scene and add the pane to it
        Scene scene = new Scene(pane, 300, 300);

        // Set the stage title and scene, and show the stage
        primaryStage.setTitle("Square to Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
