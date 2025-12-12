package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Tutorial1 extends Application {
    public void start(Stage stage) {
        Graph graph = new SingleGraph("Tutorial 1");

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");

        System.setProperty("org.graphstream.ui", "javafx");
        graph.display();
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false"); // für Sicherheit
        Application.launch(args);
    }
}