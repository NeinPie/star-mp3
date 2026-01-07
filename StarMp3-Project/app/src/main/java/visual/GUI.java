package visual;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Mp3Player;
import visual.controller.ControllerBase;
import visual.controller.MainController;
import visual.controller.SongSearchController;
import visual.views.MainView;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static visual.View.*;

/**
 * Klasse zum Verwalten aller grafischen Elemente
 */
public class GUI extends Application {
    private static Map<View, Pane> panes;
    private static Stage stage;
    private Mp3Player player;

    public static Map<View, Pane> getPanes() {
        return panes;
    }

    @Override
    public void init() {
        panes = new HashMap<>();
        player = new Mp3Player();
    }

    @Override
    public void start(Stage stage) {
        final String STYLESHEET = "style.css";
        this.stage = stage;

        ControllerBase homeController = new MainController();
        //Pane mainView = homeController.getRoot();
        //panes.put(MAIN, mainView);

        MainView mainView = new MainView();  // создаём нашу панель с графом и кнопками
        panes.put(MAIN, mainView);

        ControllerBase songSearchController = new SongSearchController();
        Pane songSearchView = songSearchController.getRoot();
        panes.put(SONGSEARCH, songSearchView);


        /**
         * Aufsetzen der Stage und Szene
         */
        Scene scene = new Scene(mainView, 960, 600);
        scene.getStylesheets().add(getClass().getResource("/"+STYLESHEET).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("SOS");
        stage.show();
        mainView.playingProperty().set(true);
    }


    /**
     * Wechseln der Root
     * @param view Enum der View zu der gewechselt werden soll
     */
    public static void switchRoot(View view) {
        Scene scene = stage.getScene();

        Pane nextRoot = panes.get(view);

        if (nextRoot != null) {
            scene.setRoot(nextRoot);
        }
    }

    /**
     * lädt ein Bild nach dessen Filenamen und skaliert es auf eine bestimmte Größe
     * @param filename Dateiname des Bildes
     * @param width neue Größe des Bildes
     * @return neues Bild als ImageView
     */
    public static ImageView loadAndResizeImage(String filename, int width){
        ImageView imageView;
        final String PATH = "/img/" + filename;

        if(GUI.class.getResource(PATH) != null){
            imageView = new ImageView(new Image(Objects.requireNonNull(GUI.class.getResourceAsStream(PATH))));
        } else {
            imageView = new ImageView(new Image(Objects.requireNonNull(GUI.class.getResourceAsStream("/img/cover_placeholder.png"))));
        }

        return loadAndResizeImage(imageView, width);
    }

    /**
     * lädt ein Bild nach dessen Byte-Array und skaliert es auf eine bestimmte Größe
     * @param imageBytes Byte-Array des Bildes
     * @param width neue Größe des Bildes
     * @return neues Bild als ImageView
     */
    public static ImageView loadAndResizeImage(byte[] imageBytes, int width) {
        ImageView imageView;
        if(imageBytes == null){
            imageView = new ImageView(new Image(Objects.requireNonNull(GUI.class.getResourceAsStream("/img/cover_placeholder.png"))));
        } else {
            ByteArrayInputStream inStream = new ByteArrayInputStream(imageBytes);
            Image image = new Image(inStream);

            imageView = new ImageView(image);
        }

        return loadAndResizeImage(imageView, width);
    }

    /** Skaliert eine ImageView auf eine bestimmte Größe
     * @param imageView Imageview
     * @param width neue Größe des Bildes
     * @return neues Bild als ImageView
     */
    public static ImageView loadAndResizeImage(ImageView imageView, int width) {
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
