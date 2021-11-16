package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ToDoList {
    public ListView<ToDoListItem> hiddenItems;
    public TextField listName;
    public ComboBox<String> listFilter;
    public ObservableList<ToDoListItem> observableListItems = FXCollections.observableArrayList();

    // Overrides toString() so that names appear in list
    public String toString() {
        return listName.textProperty().getValue();
    }

    // Constructor for item of this class
    ToDoList() {
        listName = new TextField();
        listFilter = new ComboBox<>();
        hiddenItems = new ListView<>();
    }
}
