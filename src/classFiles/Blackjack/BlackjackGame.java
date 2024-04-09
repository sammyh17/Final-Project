package src.classFiles.Blackjack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Main driver for the program.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BlackjackGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Blackjack");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            cardFunctions.main(null);
            System.out.println("Starting Blackjack game...");
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(startButton);

        Scene scene = new Scene(layout, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
     // Set the title of the stage (window)
        primaryStage.setTitle("Blackjack");
        // Set the scene for the stage
        primaryStage.setScene(scene);
        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) 
    	{
        launch(args);
    	}
        
}//End Application