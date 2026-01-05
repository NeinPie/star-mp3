package visual.views;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class MainView extends BorderPane {

    public MainView() {
        this.setId("mainRoot");
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

        graphView.setPrefSize(600, 400);
        StackPane graphWrapper = new StackPane(graphView);
        graphWrapper.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);


        BorderPane.setMargin(graphWrapper, new Insets(10));
        this.setCenter(graphWrapper);

        //init muss nach Erstellung des Unterliegenden Pane aufgerufen werden
        graphView.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.getWidth() > 0 && newBounds.getHeight() > 0) {
                graphView.init();
                graphView.update();
            }
        });

    }
}
