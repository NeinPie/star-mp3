package visual.components.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartRandomPlacementStrategy;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class GraphViewPane extends StackPane{
    Graph<String, String> g;
    SmartGraphPanel<String, String> graphView;
    public GraphViewPane() {

        try {
            setPickOnBounds(false);
            g = new GraphEdgeList<>();
            Image ima = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_blue.png"));
            Image ima2 = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_blueON.png"));
            Star2 a = new Star2(ima, ima2);
            a.setX(100);
            RotateTransition rotate = new RotateTransition();
            rotate.setByAngle(360);
            rotate.setDuration(Duration.seconds(30));
            rotate.autoReverseProperty().set(false);
            rotate.setCycleCount(TranslateTransition.INDEFINITE);
            rotate.setNode(a);
            rotate.setInterpolator(Interpolator.LINEAR);
            
            ScaleTransition scale = new ScaleTransition();
            scale.setDuration(Duration.seconds(1));
            scale.autoReverseProperty().set(false);
            scale.setCycleCount(TranslateTransition.INDEFINITE);
            scale.setByX(0.02);
            scale.setByY(0.02);
            scale.setAutoReverse(true);
            scale.setNode(a);
            scale.setInterpolator(Interpolator.EASE_BOTH);
            
            RotateTransition rotate2 = new RotateTransition();
            rotate2.setByAngle(360);
            rotate2.setDuration(Duration.seconds(1));
            rotate2.autoReverseProperty().set(false);
            rotate2.setCycleCount(TranslateTransition.INDEFINITE);
            rotate2.setNode(a);
            rotate2.setInterpolator(Interpolator.LINEAR);
            
            RotateTransition rotate3 = new RotateTransition();
            rotate3.setByAngle(180);
            rotate3.setDuration(Duration.seconds(0.5));
            rotate3.autoReverseProperty().set(false);
            rotate3.setCycleCount(1);
            rotate3.setNode(a);
            rotate3.setInterpolator(Interpolator.EASE_OUT);
            
            TranslateTransition toposition = new TranslateTransition();
            toposition.setNode(a);
            toposition.setDuration(Duration.millis(1));
            toposition.setCycleCount(1);
            toposition.setInterpolator(Interpolator.LINEAR);
            toposition.setToX(a.getX());
            toposition.setToY(a.getX());
            toposition.play();
            
            a.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                rotate.stop();
                rotate2.play();
            });
            a.addEventHandler(MouseEvent.MOUSE_EXITED, (var event) -> {
                rotate2.stop();
                rotate3.play();
                rotate3.setOnFinished(e -> {
                    rotate.play();});
            });
            rotate.play();
            this.getChildren().add(a);
            
            /*
            g.insertVertex("Test");
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
            */
            
            //        for(Vertex<String> vertex : g.vertices()){
            //
            //
            
            SmartPlacementStrategy placement =
                    new SmartRandomPlacementStrategy();
            
            graphView = new SmartGraphPanel<>(g, placement);
            
            
            graphView.setPrefSize(700, 400);
            graphView.setAutomaticLayout(true);
            
            getChildren().addAll(graphView);
            setPadding(new Insets(10));
            
            //init muss nach Erstellung des Unterliegenden Pane aufgerufen werden
            graphView.layoutBoundsProperty().addListener((obs, o, n) -> {
                if (n.getWidth() > 0 && n.getHeight() > 0) {
                    graphView.init();
                    graphView.update();
                }
            });
        } catch (FileNotFoundException ex) {
            System.getLogger(GraphViewPane.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
