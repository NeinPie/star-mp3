package visual.components.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import logic.Mp3Player;
import logic.entity.Playlist;
import logic.entity.Song;
import logic.handler.PlaylistHandler;
import visual.components.ControllerBase;
import visual.components.partials.bottompanel.BottomPanelController;


public class MainController extends ControllerBase<MainView> {
    private Mp3Player player;
    private HashMap<String, Star> StarMap;
    private Graph<String, String> g;
    private Label hoverSong;
    private Moon moon;
    private SmartGraphPanel<String, String> graphView;

    public MainController(Mp3Player player) {
        super();
        root = new MainView();
        this.player = player;
        g = root.graphViewPane.g;
        hoverSong = new Label();
        root.graphViewPane.getChildren().add(hoverSong);
        graphView = root.graphViewPane.graphView;
        init();
    }

    @Override
    public void init() {
        ControllerBase bottomPanelController = new BottomPanelController(player);
        root.setBottomPanel(bottomPanelController.getRoot());
        Playlist allSongs = PlaylistHandler.getAllSongs();
        ArrayList<Song> as;
        as = allSongs.getSongs();
        g.insertVertex("Test");
        g.insertVertex("Test2");
        g.insertEdge("Test", "Test2", "Verbindung");

        TranslateTransition hoverSong_position = new TranslateTransition();
        hoverSong_position.setNode(hoverSong);
        hoverSong_position.setDuration(Duration.millis(300));
        hoverSong_position.setCycleCount(1);
        hoverSong_position.setInterpolator(Interpolator.LINEAR);
        hoverSong_position.setToX(0);
        hoverSong_position.setToY(-200);
        hoverSong_position.play();

        int i = -1;
        for(Vertex<String> vertex : g.vertices()){
        }
        for(SmartGraphVertex<String> vertex : graphView.getSmartVertices()){
            i++;
            Image ima;
            Image ima2;
            try {
                ima = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_red.png"));
                ima2 = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_redON.png"));
                Star2 a = new Star2(ima, ima2, vertex, as.get(i));
                if(i == 1){
                    a.setX(0);
			        a.setY(0);
                }
                else{
                    a.setX(50);
			        a.setY(0);
                }
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
/*
                TranslateTransition toVertex = new TranslateTransition();
                toVertex.setNode(a);
                toVertex.setDuration(Duration.millis(300));
                toVertex.setCycleCount(1);
                toVertex.setInterpolator(Interpolator.LINEAR);
                toVertex.setToX(vertex.getPositionCenterX());
                toVertex.setToY(vertex.getPositionCenterY());
*/
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
                    hoverSong.setText(a.getSong().getTITLE());
                });
                a.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    rotate2.stop();
                    rotate3.play();
                    hoverSong.setText("");
                    rotate3.setOnFinished(e -> {
                        rotate.play();});
                });
                a.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    a.switchImage();
                    /*
                    if(a.getAct() == true){
                        Song newSong = a.getSong();
                        try {
                            player.playSong(newSong.getTITLE(), 0);
                        } catch (InvalidDataException ex) {
                            System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        } catch (UnsupportedTagException ex) {
                            System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        } catch (IOException ex) {
                            System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    }
                    else if(a.getAct() == false){
                        player.pause();
                    }
                    */
                    //toVertex.play();
			    });

                root.graphViewPane.getChildren().add(a);
                System.out.println("Wurde hinzugefuegt");
            } catch (FileNotFoundException ex) {
                System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }

    }
}
