package com.oultoncollege.model;

import java.util.ArrayList;
import com.oultoncollege.model.Task;

/**
 * Tasks.java
 * 	This wrapper for the Task model object is not used in this demo... useful as an example if we wanted 
 *  to switch to use the same logic but in another context such as CLI, Applet, Swing or even Android App. 
 * 	(Instead, with JavaFX we're relying on its built-in ObservableList to wrap our basic "Task" data model 
 * 	so we can have a full list of Tasks not just a single Task at a time).
 * 
 * @author bcopeland
 * @version 1.0.0
 * @since 2019-10-07
 */
public class Tasks {
	
	private ArrayList<Task> tasks;
	
	public Tasks() {
	}
	
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> listOfTasks) {
		this.tasks = listOfTasks;
	}

	
	@Override
	public String toString() {
		StringBuilder taskList = new StringBuilder();
		for(Task task : tasks) {
			taskList.append("Title: ");
			taskList.append(task.getTitle());
			taskList.append(" | Added: ");
			taskList.append(task.getAddedTimestamp());
			taskList.append( " | Due: ");
			taskList.append(task.getDueTimestamp()); 
		}
		return taskList.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
		   return false;
		}
		if (getClass() != obj.getClass()) {
		   return false;
		}
		
		int index = 0;
		final Tasks otherTasks = (Tasks) obj;
		for (Task otherTask : otherTasks.getTasks()) {
			if (!otherTask.getTitle().equals(tasks.get(index).getTitle())) {
				return false;
			} else if (!otherTask.getAddedTimestamp().equals(tasks.get(index).getAddedTimestamp())) {
				return false;
			} else if (!otherTask.getDueTimestamp().equals(tasks.get(index).getDueTimestamp())) {
				return false;
			}
			index++;
		}
		return true;
	}
		
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 19 * hash + (this.tasks != null ? this.tasks.hashCode() : 0);
		return hash;
	}

}
