package visual.components.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

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
    private HashMap<String, Star2> StarMap;
    private Graph<String, String> g;
    private Label hoverSong;
    private Moon moon;
    private SmartGraphPanel<String, String> graphView;
    private DraggableMaker draggablemaker;

    public MainController(Mp3Player player) {
        super();
        root = new MainView();
        this.player = player;
        g = root.graphViewPane.g;
        hoverSong = new Label();
        root.graphViewPane.getChildren().add(hoverSong);
        graphView = root.graphViewPane.graphView;
        draggablemaker = new DraggableMaker();
        StarMap = new HashMap();
        init();
    }

    @Override
    public void init() {
        ControllerBase bottomPanelController = new BottomPanelController(player);
        root.setBottomPanel(bottomPanelController.getRoot());
        Playlist allSongs = PlaylistHandler.getAllSongs();
        ArrayList<Song> as;
        as = allSongs.getSongs();

        TranslateTransition hoverSong_position = new TranslateTransition();
        hoverSong_position.setNode(hoverSong);
        hoverSong_position.setDuration(Duration.millis(300));
        hoverSong_position.setCycleCount(1);
        hoverSong_position.setInterpolator(Interpolator.LINEAR);
        hoverSong_position.setToX(0);
        hoverSong_position.setToY(-200);
        hoverSong_position.play();
    
        Image ima;
        try {
            ima = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_red.png"));
            Image ima2 = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_redON.png"));
            Star2 a = new Star2(ima, ima2);
            a.setX(-100);
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
                    toposition.setToY(a.getY());
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
                    });
                    rotate.play();
                    StarMap.put("0", a);
                    root.graphViewPane.getChildren().add(a);

                    g.insertVertex("Test");
                    for(Vertex<String> vertex : g.vertices()){
                        a.setNode(vertex);
                    }
                    a.setSong(as.get(0));
                    g.insertVertex("Test2");


        } catch (FileNotFoundException ex) {
            System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

}

/*
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
            i++;
            Image ima;
            Image ima2;
            SmartGraphVertex<String> vertex2 = null;
            for(SmartGraphVertex<String> v : graphView.getSmartVertices()){
                Vertex<String> originVertex = v.getUnderlyingVertex();
                if(originVertex.element().equals(vertex.element())){
                    vertex2 = v;
                }
            }
            if(vertex2== null){
                try {
                    ima = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_red.png"));
                    ima2 = new Image(new FileInputStream("C:\\Users\\MiniCoopa\\Desktop\\Studium zeug\\Semester 3\\ineratcive Anwendungen\\star-mp3\\StarMp3-Project\\app\\src\\main\\resources\\img\\stars\\star_redON.png"));
                    Star a = new Star(ima, ima2, vertex, vertex2, as.get(i));
                    //a.setX(vertex2.getPositionCenterX());
                    //a.setY(vertex2.getPositionCenterY());
                    var rotate = new RotateTransition();
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
                    });
                    rotate.play();
                    root.graphViewPane.getChildren().add(a);
                } catch (FileNotFoundException ex) {
                    System.getLogger(MainController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
    }

*/
