package ucf.assignments;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */

public class ToDoListItem {
    public TextField itemName, itemDescription;
    public DatePicker itemDueDate;
    public CheckBox completed;

    // Overrides toString() so that names appear in list

    public String toString() {
        return itemName.textProperty().getValue();
    }

    // Constructor for item of this class
    ToDoListItem(){
        itemName = new TextField();
        itemDescription = new TextField();
        itemDueDate = new DatePicker();
        completed = new CheckBox();
    }
}
