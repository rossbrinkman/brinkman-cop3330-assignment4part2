package tests;

import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */


class ControllerTest {


    @Test // Make sure the size of the list is bigger after this function is called
    void addToDoListItem() {
        Controller controller = new Controller();
        int before = controller.toDoItems.getItems().size();
        controller.addToDoListItem();
        int after = controller.toDoItems.getItems().size();
        assertTrue(before > after);
    }

    @Test // Make sure the size of the list is bigger after this function is called
    void createNewList() {
        Controller controller = new Controller();
        int before = controller.toDoLists.getItems().size();
        controller.createNewList();
        int after = controller.toDoLists.getItems().size();
        assertTrue(before > after);
    }

    @Test // Make sure the items of this list are completed after this function is called with the "Completed" filter
    void filterList() {
        Controller controller = new Controller();
        controller.listFilter.setValue("Completed");
        controller.filterList();
        assertTrue(controller.toDoItems.getItems().get(0).completed.isSelected());
    }

    @Test // Make sure the size of the list is smaller after this function is called
    void removeList() {
        Controller controller = new Controller();
        int before = controller.toDoLists.getItems().size();
        controller.removeList();
        int after = controller.toDoLists.getItems().size();
        assertTrue(before > after);
    }

    @Test // Make sure the size of the list is smaller after this function is called
    void removeToDoListItem() {
        Controller controller = new Controller();
        int before = controller.toDoItems.getItems().size();
        controller.removeList();
        int after = controller.toDoItems.getItems().size();
        assertTrue(before > after);
    }

    @Test // Make sure the size of the list is 0 after this function is called
    void clearToDoList() {
        Controller controller = new Controller();
        controller.removeList();
        int after = controller.toDoItems.getItems().size();
        assertEquals(0, after);
    }

    @Test // Make sure this function finishes with the "Success" return statement
    void saveToDoList() throws IOException {
        Controller controller = new Controller();
        assertEquals("Success", controller.saveToDoList());
    }

    @Test // Make sure this function finishes with the "Success" return statement
    void openNewListsFromFile() throws FileNotFoundException {
        Controller controller = new Controller();
        assertEquals("Success", controller.openNewListsFromFile());
    }
}