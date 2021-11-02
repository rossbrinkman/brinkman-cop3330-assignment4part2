package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */

class ControllerTest {

    @Test
    void addToDoListItem() {
        //previousCount = Controller.toDoListItems.Count
        //Controller.addToDoListItem();
        //assertTrue(previousCount < Controller.toDoListItems.Count)
    }

    @Test
    void createNewList() {
        //previousCount = Controller.toDoLists.Count
        //Controller.createNewList();
        //assertTrue(previousCount < Controller.toDoLists.Count)
    }

    @Test
    void onListNameChanged() {
        /*
        String prevName = toDoList.listName
        Controller.onListNameChanged()
        assertFalse(prevName == toDoList.listName)
        */
    }

    @Test
    void onItemDescriptionChanged() {
        /*
        String prevDescription = toDoListItem.itemDescription
        Controller.onItemDescriptionChanged()
        assertFalse(prevDescription == toDoListItem.itemDescription)
        */
    }

    @Test
    void itemDueDateChanged() {
        /*
        * DueDate dd = item.itemDueDate;
        * itemDueDateChanged()
        * assertFalse(dd == item.itemDueDate)
        */
    }

    @Test
    void itemCompleted() {
        /*
        * Boolean completed = getItem from selectedID completed
        * itemCompleted()
        * assertFalse(completed == getItem from selectedID completed)
        */
    }

    @Test
    void removeList() {
        /*
        removeList()
        assertTrue(toDoLists.getItems() get selectedID == null)
        */
    }

    @Test
    void removeToDoListItem() {
        /*
        removeToDoListItem()
        assertTrue(toDoListsItems get selectedID == null)
        */
    }

    @Test
    void onListFilterChanged() {
        /*
            String value = find current filter value
            onListFilterChanged()
            assertTrue(value != find current filter value)
        */
    }

    @Test
    void onSave() {
        /*
            String objectName = find name of list of lists
            onSave()
            assertTrue(check if .json with objectName as file name exists)
        */
    }

    @Test
    void saveToDoList() {
        /*
            String listName = find name of list
            saveToDoList()
            assertTrue(check if .json with listName as file name exists)
        */
    }

    @Test
    void openNewListsFromFile() {
        /*
            int prevListCount = Controller.toDoLists.Count();
            openNewListsFromFile()
            assertTrue(prevListCount < Controller.toDoLists.Count())
        */
    }

    @Test
    void convertFilesToJson() {
        /*
            List<Controller.ToDoList> newLists = convertFilesToJson("putPathHere.json");
            assertTrue(newLists.isEmpty() = false);
        */
    }
}