package com.oultoncollege.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Task.java
 * 	Task object which models an individual Task in our "ToDo List".
 *   
 * @author bcopeland
 * @version 1.0.0
 * @since 2019-10-07
 */
public class Task {

	/* Task properties */
	private String title;
	private String addedTimestamp;
	private String dueTimestamp;
	private final int DAYS_IN_WEEK = 7;
	
	
	public Task() {
		//diagnotic purposes only to show Task object creation
		System.out.println("Task object created");
	}
	
	public Task(String title, LocalDateTime added, LocalDateTime due) {
		setTitle(title);
		setAddedTimestamp(added);
		setDueTimestamp(due);
		//diagnotic purposes only to show Task object creation
		System.out.println("Task object created via preset values: " + this.title + "," + this.addedTimestamp + "," + this.dueTimestamp);
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String taskTitle) {
		this.title = taskTitle;
	}
	
	public String getAddedTimestamp() {
		return addedTimestamp;
	}
	
	public void setAddedTimestamp(LocalDateTime taskAddedTimestamp) {
		if (null != taskAddedTimestamp) {
			//use the passed in Timestamp indicating when the Task was added
			this.addedTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(taskAddedTimestamp);
		} else {
			//fallback to generating our own Timestamp from the current DateTime
			this.addedTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());			
		}
	}
	
	// TODO: "DueDate" not implemented yet in GUI... but could add a DateTime input to let user choose an "End Date" if continuing to expand on this demo, such as to add a Reminder feature or just display the "Due By" date)
	public String getDueTimestamp() {
		return dueTimestamp;
	}
	
	public void setDueTimestamp(LocalDateTime taskDueTimestamp) {
		if (null != taskDueTimestamp) {
			//use the passed in Timestamp indicating when the Task should be completed by
			this.dueTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(taskDueTimestamp);
		} else {
			LocalDateTime today =  LocalDateTime.now(); //Today
			LocalDateTime nextWeek = today.plusDays(DAYS_IN_WEEK); //Plus 7 days
			//fallback to generating our own Timestamp from the current DateTime + 7 days (give ourselves 1 week in the future to complete the Task)
			this.dueTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(nextWeek);			
		}
	}
	
}
