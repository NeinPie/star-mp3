package org.example;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Tutorial1 extends Application {
    public void start(Stage stage) {

        Graph<String, String> g = new GraphEdgeList<>();

        // Knoten
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");

        // Kanten
        g.insertEdge("A", "B", "AB");
        g.insertEdge("B", "C", "BC");
        g.insertEdge("C", "A", "CA");

        SmartPlacementStrategy placement =
                new SmartCircularSortedPlacementStrategy();

        SmartGraphPanel<String, String> graphView =
                new SmartGraphPanel<>(g, placement);

        Scene scene = new Scene(graphView, 1024, 768);

        stage.setTitle("JavaFX Graph Visualization");
        stage.setScene(scene);
        stage.show();

        graphView.init();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}