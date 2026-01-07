package visual.views;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartRandomPlacementStrategy;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import visual.views.partials.ControlBar;

public class MainView extends BorderPane {
    public HBox controlBar;

    public MainView() {
        controlBar = new ControlBar();

        this.setId("mainRoot");
        Graph<String, String> g = new GraphEdgeList<>();

        g.insertVertex("Alphabet");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");
        g.insertVertex("G");
        g.insertVertex("H");
        g.insertEdge("Alphabet", "B", "AB");
        g.insertEdge("Alphabet", "C", "AC");
        g.insertEdge("B", "C", "BC");
        g.insertEdge("C", "D", "CD");
        g.insertEdge("B", "E", "BE");
        g.insertEdge("F", "D", "DF");
        g.insertEdge("F", "G", "GF");
        g.insertEdge("F", "H", "HF");
        g.insertEdge("H", "C", "HC");

//        for(Vertex<String> vertex : g.vertices()){
//
//        }
        SmartPlacementStrategy placement =
                new SmartRandomPlacementStrategy();

        SmartGraphPanel<String, String> graphView = new SmartGraphPanel<>(g, placement);

        graphView.setPrefSize(600, 400);
        StackPane graphWrapper = new StackPane(graphView);
        graphWrapper.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);


        BorderPane.setMargin(graphWrapper, new Insets(10));
        this.setBottom(controlBar);
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
