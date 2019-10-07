package com.oultoncollege.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import com.oultoncollege.model.Task;
import com.oultoncollege.model.Tasks;


/**
 * ListController.java
 * 	This is the main ToDo list controller which handles two-way data binding between the 
 *  FXML View definition (GUI components) with CSS View styling (GUI look & feel), and the Java 
 *  data Model.
 * 
 * @author bcopeland
 * @version 1.0.0
 * @since 2019-10-07
 */
public class ListController implements Initializable {

	/*******************/
	/* Data properties */
	/*******************/
	@FXML
	TextField inputText;

	@FXML
	ComboBox todoList;

	// reference the default Collection of task items created via the hardcoded list in FXML
    @FXML
    ObservableList tasks;

	// an observable list with default values (an alternative way of passing default values programmatically)
/*	Task firstTask = new Task("Task 1", null, null);
	Task secondTask = new Task("Task 2", null, null);
	ObservableList<Task> tasks = FXCollections.observableArrayList(firstTask, secondTask); */
	
    //two-way data binding for Task addition and removal (works by a match in the Text displayed in the Textfield)
	private StringProperty taskInput = new SimpleStringProperty("");

	
	/*************************/
	/* Button event handlers */
	/*************************/
	@FXML
	private void addButtonHandler(ActionEvent event) {
		String item = inputText.getText();
		
		// do a quick sanity check before adding the text to the list
		if (item.equals("") || tasks.contains(item)) {
			return;
		}
		
		// bind to the collection defined in the FXML file
		tasks.add(item);
		
		//set the currently selected item to the one just added
		todoList.setValue(item);
	}
	
	@FXML
	private void removeButtonHandler(ActionEvent event) {
		String item = inputText.getText();
		
		// do a quick sanity check before trying to remove the Text from the list
		if (item.equals("") || !tasks.contains(item)) {
			return;
		}
		
		// unbind and remove item from collection defined in the FXML file
		tasks.remove(item);
	}
	
	@FXML
	private void listSelectHandler(ActionEvent event) {
		//gets the item currently selected in the dropdown
		Object selected = todoList.getSelectionModel().getSelectedItem();
		
		// if the dropdown trying to be pushed to the Textfield is not empty, synch them up (so removal will remove the intended item) 
		if (selected != null) {
			inputText.setText(""+selected);
		}
	}
	
	@FXML
	private void exitButtonHandler(ActionEvent event) {
        Platform.exit();
    }
	
	
	/********************************/
	/* Controller getters & setters */
	/********************************/
	public void initialize(URL location, ResourceBundle resources) {
		//the below is a critical line where we tell JavaFX that this data property will be a two-way binding (can change values through input and be changed through other events' output)
		inputText.textProperty().bindBidirectional(taskInputProperty());
		
		// the following line only needed if linking sample programmatic "hard-coded" (or say DB-retrieved) initial Collection to the ComboBox at startup
//		todoList.setItems(tasks);
	}

	public String getTaskInput() {
		return taskInput.get();
	}
	public void setTaskInput(String twoWayInput) {
		this.taskInput.set(twoWayInput);
	}
	
	public StringProperty taskInputProperty() {
		return taskInput;
	}
	

	/* wrap list of Task items into the ObservableList data model that allows two-way binding */
	public ObservableList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ObservableList<Task> tasks) {
		this.tasks = tasks;
	}

}
