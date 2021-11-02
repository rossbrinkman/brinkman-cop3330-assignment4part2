/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ross Brinkman
 */

module ucf.assignments {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.junit.jupiter.api;


    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments;
}