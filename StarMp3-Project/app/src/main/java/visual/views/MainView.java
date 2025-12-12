package visual.views;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;

public class MainView extends BorderPane {
    Label label;

    public MainView() {
        this.setId("mainRoot");
        label = new Label("oben");

        Graph graph = new SingleGraph("Graph");
        graph.addNode("A");
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addNode("D");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.addEdge("CB", "C", "D");


        System.setProperty("org.graphstream.ui", "javafx");
        FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        FxViewPanel panel = (FxViewPanel) viewer.addDefaultView(false);
        this.setTop(label);
        this.setCenter(panel);
        this.setBottom(new Label("unten"));
    }
}
