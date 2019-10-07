package com.oultoncollege;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * ToDo.java
 * 	Application runner to display the ToDo List GUI.
 * 	
 * @author bcopeland
 * @version 1.0.0
 * @since 2019-10-07
 */
public class ToDo extends Application {
	
	//define app window offsets here
    private double xOffset = 0;
    private double yOffset = 0;
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	//map this ToDo List "App Launcher" code to the FXML 
        Parent root = FXMLLoader.load(getClass().getResource("ToDo.fxml"));

		//grab app root here to detect start of mouse click
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		//move GUI around by detecting mouse movements until button released
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent event) {
		        primaryStage.setX(event.getScreenX() - xOffset);
		        primaryStage.setY(event.getScreenY() - yOffset);
		    }
		});
		
        //set the scene's root and title
        primaryStage.setScene(new Scene(root, 200, 100));
        primaryStage.setTitle("ToDo List");

        //reference external CSS to enhance the basic JavaFX GUI (just a small example of changing a button)
        primaryStage.getScene().getStylesheets().add(getClass().getResource("ToDo.css").toExternalForm());
        
        //remove default "window decoration" (Title bar, Minimize/Resize/Close)
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        //display GUI
        primaryStage.show();
    }


    /* make GUI launchable via command-line */
    public static void main(String[] args) {
        launch(args);
    }

}
