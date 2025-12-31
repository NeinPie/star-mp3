//package visual.views;
//
//import javafx.scene.control.Label;
//import javafx.scene.layout.*;
//import org.graphstream.graph.Graph;
//import org.graphstream.graph.implementations.SingleGraph;
//import org.graphstream.stream.ProxyPipe;
//import org.graphstream.stream.Source;
//import org.graphstream.ui.fx_viewer.FxViewPanel;
//import org.graphstream.ui.fx_viewer.FxViewer;
//import org.graphstream.ui.graphicGraph.GraphicGraph;
//import org.graphstream.ui.view.GraphRenderer;
//import org.graphstream.ui.view.LayerRenderer;
//
//import java.awt.*;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
//import java.io.IOException;
//import java.io.InputStream;
//import javafx.scene.image.Image;
//import org.graphstream.ui.view.Viewer;
//
//import java.nio.charset.StandardCharsets;
//
//public class MainView extends BorderPane {
//
//    public MainView() {
//        this.setId("mainRoot");
//
//        Graph graph = new SingleGraph("Graph");
//
//        FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
//
//        FxViewPanel panel = (FxViewPanel) viewer.addDefaultView(false);
//        panel.setStyle("-fx-background-color: transparent;");
//        this.setCenter(panel);
//
//
//        InputStream cssStream = getClass().getResourceAsStream("/graph-style.css");
//        if(cssStream != null) {
//            String cssText="";
//            try{
//                cssText = new String(cssStream.readAllBytes(), StandardCharsets.UTF_8);
//                graph.setAttribute("ui.stylesheet", cssText);
//            } catch (IOException e) {
//                System.out.println("ERROR: CSS File "+cssText + " konnte nicht gefunden werden");
//            }
//        } else {
//            System.out.println("CSS nicht gefunden.");
//        }
//
//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("C");
//        graph.addEdge("AB", "A", "B");
//        graph.addEdge("BC", "B", "C");
//        graph.addEdge("CA", "C", "A");
//        viewer.enableAutoLayout();
//
//        this.setStyle("-fx-background-color: transparent;");
//
//    }
//}
