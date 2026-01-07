package visual.views.partials;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.*;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;

public class GraphViewPane extends StackPane{
    public GraphViewPane() {

        setPickOnBounds(false);
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
      // 

        SmartPlacementStrategy placement =
                new SmartCircularSortedPlacementStrategy();

        SmartGraphPanel<String, String> graphView =
                new SmartGraphPanel<>(g, placement);
        

        graphView.setPrefSize(700, 500);

        getChildren().addAll(graphView);
        setPadding(new Insets(10));

        //init muss nach Erstellung des Unterliegenden Pane aufgerufen werden
        graphView.layoutBoundsProperty().addListener((obs, o, n) -> {
            if (n.getWidth() > 0 && n.getHeight() > 0) {
                graphView.init();
                graphView.update();
            }
        });
    }
}
