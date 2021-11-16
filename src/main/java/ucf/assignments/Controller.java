package ucf.assignments;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.*;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */

public class Controller implements Initializable {

    @FXML
    public DatePicker itemDueDate;
    @FXML
    public TextField itemName;
    @FXML
    public CheckBox itemComplete;
    @FXML
    public TextArea itemDescription;
    @FXML
    public ComboBox<String> listFilter;
    @FXML
    public TextField listName;
    @FXML
    public ListView<ToDoListItem> toDoItems;
    @FXML
    public ListView<ToDoList> toDoLists;
    @FXML
    public AnchorPane listInspector;
    @FXML
    public AnchorPane toDoInspector;
    @FXML
    public AnchorPane toDoPane;
    @FXML
    public Group toDoButtons;

    public ToDoList currentlySelected;
    public ToDoListItem currentlySelectedItem;
    int listSize, itemListSize;

    // This is essentially a start function. The list filter is assigned here.
    // Also, event listeners are added to several values to make sure we know when the values are changed
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listFilter.getItems().addAll("All", "Completed", "Incomplete");
        listFilter.getSelectionModel().select("All");

        itemDescription.textProperty().addListener((ov, oldValue, newValue) -> {
            if (itemDescription.getText().length() > 256) {
                String s = itemDescription.getText().substring(0, 256);
                itemDescription.setText(s);
            }
        });

        itemComplete.selectedProperty().addListener((observable, oldValue, newValue) -> CheckFilter());

        listFilter.valueProperty().addListener((observable, oldValue, newValue) -> filterList());

        toDoLists.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onSelectionChanged());

        toDoItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> onItemSelectionChanged());
    }

    @FXML
    public void addToDoListItem() {

        ToDoListItem toDoListItem = new ToDoListItem();
        ToDoListItem selectedItem = toDoItems.getSelectionModel().getSelectedItem(); // select the new item

        if(selectedItem != null) { // Unbind gui properties so that they can be reassigned to the
            selectedItem.itemName.textProperty().unbind();          // currently selected object
            selectedItem.itemDescription.textProperty().unbind();
            selectedItem.completed.selectedProperty().unbind();
            toDoListItem.itemDueDate.valueProperty().unbind();
        }
        ResetItemInspector();
        if(itemListSize > 0) // Assign names to the new object
            itemName.setText("New Item (" + itemListSize + ")");
        else {
            itemName.setText("New Item");
            toDoInspector.setDisable(false);
        }

        // Rebind the GUI properties to the newly selected properties
        toDoListItem.itemName.textProperty().bind(itemName.textProperty());
        toDoListItem.itemDescription.textProperty().bind(itemDescription.textProperty());
        toDoListItem.completed.selectedProperty().bind(itemComplete.selectedProperty());
        toDoListItem.itemDueDate.valueProperty().bind(itemDueDate.valueProperty());

        toDoItems.getItems().add(toDoListItem);
        toDoItems.getSelectionModel().select(toDoListItem);

        itemListSize++; //increment list size
    }

    void ResetItemInspector()
    {
        itemComplete.setSelected(false);
        itemName.setText("");
        itemDescription.setText("");
        itemDueDate.setValue(null);
    }

    @FXML
    public void createNewList() {
        // Create a new list and select it
        ToDoList toDoList = new ToDoList();
        ToDoList selectedList = toDoLists.getSelectionModel().getSelectedItem();
        toDoItems.setItems(toDoList.observableListItems);
        itemListSize = 0;

        // unbind appropriate properties
        if(selectedList != null)
            selectedList.listName.textProperty().unbind();
        if(listSize > 0) // Assign list name
            listName.setText("New List (" + toDoLists.getItems().size() + ")");
        else {
            listName.setText("New List");
            toDoList.listFilter = listFilter;
            listInspector.setDisable(false);
            toDoPane.setDisable(false);
        }

        // Bind appropriate properties
        toDoList.listName.textProperty().bind(listName.textProperty());

        // add list to gui and increment list count
        toDoLists.getItems().add(toDoList);
        toDoLists.getSelectionModel().select(toDoList);
        listSize++;
    }

    // This function rebinds and reassigns the appropriate values in the GUI when the list selection changes
    void onSelectionChanged() {
        if(currentlySelected != null && currentlySelected.listName.textProperty().isBound())
            currentlySelected.listName.textProperty().unbind();

        listFilter.setValue("All");
        CheckFilter();
        ToDoList selectedList = toDoLists.getSelectionModel().getSelectedItem();
        if(selectedList != null) {
            currentlySelected = selectedList;
            listName.setText(selectedList.listName.getText());
            selectedList.listName.textProperty().bind(listName.textProperty());

            toDoItems.setItems(selectedList.observableListItems);
            itemListSize = toDoItems.getItems().size();
            if(itemListSize == 0)
                ResetItemInspector();
        }
    }

    // This function rebinds and reassigns the appropriate values in the GUI when the item list selection changes
    void onItemSelectionChanged() {
        if(currentlySelectedItem != null) {
            if (currentlySelectedItem.itemName.textProperty().isBound())
                currentlySelectedItem.itemName.textProperty().unbind();
            if (currentlySelectedItem.itemDescription.textProperty().isBound())
                currentlySelectedItem.itemDescription.textProperty().unbind();
            if (currentlySelectedItem.itemDueDate.valueProperty().isBound())
                currentlySelectedItem.itemDueDate.valueProperty().unbind();
            if (currentlySelectedItem.completed.selectedProperty().isBound())
                currentlySelectedItem.completed.selectedProperty().unbind();
        }

        ToDoListItem selectedItem = toDoItems.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            currentlySelectedItem = selectedItem;
            itemName.setText(selectedItem.itemName.getText());
            itemDescription.setText(selectedItem.itemDescription.getText());
            itemComplete.setSelected(selectedItem.completed.isSelected());
            itemDueDate.setValue(selectedItem.itemDueDate.getValue());
            selectedItem.itemName.textProperty().bind(itemName.textProperty());
            selectedItem.itemDescription.textProperty().bind(itemDescription.textProperty());
            selectedItem.completed.selectedProperty().bind(itemComplete.selectedProperty());
            selectedItem.itemDueDate.valueProperty().bind(itemDueDate.valueProperty());
        }
    }

    // Handles the list filtering when the filter value is changed
    public void filterList()
    {
        if(Objects.equals(listFilter.getValue(), "All")) {
            toDoButtons.setDisable(false);
            makeAllVisible();
        }
        else if(Objects.equals(listFilter.getValue(), "Completed")) {
            toDoButtons.setDisable(true);
            makeAllVisible();
            makeCompletedVisible();
        }
        else if(Objects.equals(listFilter.getValue(), "Incomplete")) {
            toDoButtons.setDisable(true);
            makeAllVisible();
            makeIncompleteVisible();
        }
    }

    // Make only the incomplete items visible
    void makeIncompleteVisible()
    {
        for(int i = 0; i < toDoItems.getItems().size(); i++)
        {
            ToDoListItem temp = toDoItems.getItems().get(i);
            if(temp.completed.isSelected())
            {
                currentlySelected.hiddenItems.getItems().add(temp);
                toDoItems.getItems().remove(temp);
                i--;
            }
        }
        itemListSize = toDoItems.getItems().size();
        CheckItemCount();
    }

    // Make only the completed items visible
    void makeCompletedVisible()
    {
        for(int i = 0; i < toDoItems.getItems().size(); i++)
        {
            ToDoListItem temp = toDoItems.getItems().get(i);
            if(!temp.completed.isSelected())
            {
                currentlySelected.hiddenItems.getItems().add(temp);
                toDoItems.getItems().remove(temp);
                i--;
            }
        }
        itemListSize = toDoItems.getItems().size();
        CheckItemCount();
    }

    // Make all the items visible
    void makeAllVisible()
    {
        System.out.print(currentlySelected.hiddenItems.getItems().size());
        if(currentlySelected.hiddenItems.getItems().size() != 0) {
            for(int i = 0; i < currentlySelected.hiddenItems.getItems().size(); i++)
            {
                ToDoListItem temp = currentlySelected.hiddenItems.getItems().get(i);
                toDoItems.getItems().add(temp);
                currentlySelected.hiddenItems.getItems().remove(temp);
                i--;
            }
            toDoInspector.setDisable(false);
            toDoItems.getSelectionModel().select(0);
        }
        System.out.print(currentlySelected.hiddenItems.getItems().size() + "\n\n");
        itemListSize = toDoItems.getItems().size();
        CheckItemCount();
    }

    // If an item's completed value changes, check what filter is active, and make the item disapear if needed
    void CheckFilter()
    {
        if(Objects.equals(listFilter.getValue(), "Incomplete") && currentlySelectedItem.completed.isSelected())
        {
            currentlySelected.hiddenItems.getItems().add(currentlySelectedItem);
            toDoItems.getSelectionModel().select(currentlySelectedItem);
            removeToDoListItem();
        }
        else if(Objects.equals(listFilter.getValue(), "Completed") && !currentlySelectedItem.completed.isSelected())
        {
            currentlySelected.hiddenItems.getItems().add(currentlySelectedItem);
            toDoItems.getSelectionModel().select(currentlySelectedItem);
            removeToDoListItem();
        }
    }

    //Checks how many items there are and disables the itemInspector if there are 0
    void CheckItemCount()
    {
        toDoInspector.setDisable(itemListSize == 0);
    }

    @FXML
    public void removeList() {
        toDoLists.getItems().remove(currentlySelected);
        listSize--;
        if(listSize == 0)
        {
            listInspector.setDisable(true);
            toDoPane.setDisable(true);
            toDoInspector.setDisable(true);
        }
    }

    @FXML
    public void removeToDoListItem() {
        toDoItems.getItems().remove(currentlySelectedItem);
        itemListSize--;
        CheckItemCount();
    }

    @FXML
    public void clearToDoList() {
        for(int i=toDoItems.getItems().size()-1; i >= 0 ; i--)
        {
            toDoItems.getItems().remove(i);
            itemListSize--;
            itemName.setText("");
            toDoInspector.setDisable(true);
        }
    }

    @FXML
    public String saveToDoList() throws IOException {
        // Create a new file chooser and open a save file dialog
        File initialDirectory = new File("src\\main\\java");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save List");
        fileChooser.setInitialDirectory(initialDirectory);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt*"));
        File file = fileChooser.showSaveDialog(new Stage());
        if(file == null)
            return "Failed";
        file = new File(file.getPath() + ".txt");


        // Convert the list into a string
        String dataAsString = putListDataIntoString();

        // Write the string to a text file
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(dataAsString);
        printWriter.close();

        return "Success";
    }

    @FXML
    public String openNewListsFromFile() throws FileNotFoundException {
        // Create a new file chooser with open file dialog
        File initialDirectory = new File("src\\main\\java");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open List");
        fileChooser.setInitialDirectory(initialDirectory);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt*"));
        File file = fileChooser.showOpenDialog(new Stage());
        if(file == null)
            return "Failed";

        // Convert the text file into a list and add the list to the GUI
        ToDoList toDoList = putStringIntoNewList(file);
        toDoLists.getItems().add(toDoList);
        listSize++;
        toDoLists.getSelectionModel().select(toDoList);
        toDoPane.setDisable(false);
        listInspector.setDisable(false);
        if(toDoList.observableListItems.size() != 0)
            toDoInspector.setDisable(false);

        return "Success";
    }

    public String putListDataIntoString() {
        StringBuilder list;

        list = new StringBuilder(currentlySelected.listName.getText() + "\n");
        list.append(currentlySelected.listFilter.getSelectionModel().getSelectedItem()).append("\n");

        String prevValue = listFilter.getSelectionModel().getSelectedItem();
        makeAllVisible();
        for(ToDoListItem item : currentlySelected.observableListItems)
        {
            list.append(item.completed.isSelected()).append("\n")
                    .append(item.itemName.getText()).append("\n").append(item.itemDescription.getText())
                    .append("\n").append(item.itemDueDate.getValue()).append("\n");
        }
        listFilter.setValue(prevValue);
        filterList();

        return list.toString();
    }

    public ToDoList putStringIntoNewList(File data) throws FileNotFoundException {
        ToDoList newList = new ToDoList();

        Scanner scanner = new Scanner(data);
        newList.listName.setText(scanner.nextLine());
        newList.listFilter.setValue(scanner.nextLine());

        while (scanner.hasNext())
        {
            ToDoListItem newItem = new ToDoListItem();
            newItem.completed.setSelected(scanner.nextBoolean());
            scanner.nextLine();
            newItem.itemName.setText(scanner.nextLine());
            newItem.itemDescription.setText(scanner.nextLine());
            newItem.itemDueDate.setAccessibleText(scanner.nextLine());
            newList.observableListItems.add(newItem);
        }
        return newList;
    }
}
