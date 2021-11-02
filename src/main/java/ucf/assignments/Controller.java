package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import com.google.gson.Gson;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */

public class Controller {

    @FXML
    private DatePicker itemDueDate;

    @FXML
    private TextField itemName;

    @FXML
    private ComboBox<String> listFilter;

    @FXML
    private TextField listName;

    @FXML
    private ListView<ToDoListItem> toDoItems;

    @FXML
    private ListView<ToDoList> toDoLists;

    //private int listSelectedID = toDoLists.getSelectionModel().getSelectedIndex();
    //private int itemSelectedID = toDoLists.toDoItems().getSelectionModel().getSelectedIndex();

    @FXML
    void addToDoListItem(MouseEvent event) {
        /*
        * ToDoListItem toDoListItem = new ToDoListItem();
        * toDoListItem.itemName = itemName;
        * toDoListItem.itemDescription = itemDescription;
        * toDoListItem.itemDueDate = itemDueDate;
        *
        * Add toDoListItem to toDoItems
        */
    }

    @FXML
    void createNewList(MouseEvent event) {
        /*
        ToDoList toDoList = new ToDoList();
        toDoList.listName = listName;
        toDoList.listFilter = listFilter;

        Add toDoList to toDoLists;
        */
    }

    @FXML
    void onListNameChanged(InputMethodEvent event) {
        /*
        toDoLists.getItems() find selected toDoList
        toDoList.listName = get changed value
        */
    }

    @FXML
    void onItemDescriptionChanged(InputMethodEvent event) {
        /*
        toDoItems.getItems(itemsSelectedID) find selected toDoItem
        toDoItems.itemDescription = get changed value
        */
    }

    @FXML
    void itemDueDateChanged(InputMethodEvent event) {
        // item = get item using itemSelectedID
        // item.itemDueDate = get changed value;
    }

    @FXML
    void itemCompleted(MouseEvent event) {
        /*
        *if (getItem from selectedID) completed is true
        *   item.completed = false
        *else
        *   item.completed = true
        */
    }

    @FXML
    void removeList(MouseEvent event) {
        /*
        toDoLists.getItems().remove(selectedID);
        */
    }

    @FXML
    void removeToDoListItem(MouseEvent event) {
        /*
        int selectedID = toDoItems.getSelectionModel().getSelectedIndex();
        toDoLists.toDoItems().remove(selectedID);
        */
    }

    @FXML
    void onListFilterChanged(InputMethodEvent event) {
        /*
        * find value it was changed to
        * if (changedValue == "completed tasks")
        *   for each item in list
        *       if (!completed)
        *           hidden = true;
        * else if (changed value == "incomplete tasks")
        *   for each item in list
        *       if (completed)
        *           hidden = true;
        * else if (changed value == "date")
        *    list.sort(date);
        *
        *  else
        *       for each item in list
        *           hidden = false;
        */
    }

    @FXML
    void onSave(ActionEvent event) {
        /*
        *String json = new Gson().toJson(toDoLists);
        *
        *FileWriter fileWriter = new FileWriter(toDoLists);
        *PrintWriter printWriter = new PrintWriter(fileWriter);
        *printWriter.print(json);
        *printWriter.close();
        */
    }

    @FXML
    void saveToDoList(MouseEvent event) {
        /*
        listToConvert = get ToDoList from toDoLists using selectedID;
        String json = new Gson().toJson(ToDoList);

        FileWriter fileWriter = new FileWriter(listToConvert);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(json);
        printWriter.close();
        */
    }

    @FXML
    void openNewListsFromFile(MouseEvent event)
    {
        /*
        * Open file chooser
        * Add extension filter for .json files
        * List<File> files = Show open multiple file dialog
        * for each File in files
        * List<ToDoList> newLists = ConvertFilesToJson(files)
        * add newLists to toDoLists
        */
    }

    List<ToDoList> ConvertFilesToJson(Path path)
    {
        /*
        Gson gson = new Gson();
        File jsonFile = new File(path);
        Scanner sc = new Scanner(file);
        String jsonInString;

        while(sc.hasNext())
	        jsonInString.Add(sc.next());

        ToDoList list= gson.fromJson(jsonInString, ToDoList.class);
        */
        return new ArrayList<>();
    }
}
