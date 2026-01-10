package visual.components.main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import visual.components.BorderPaneWithBottomPanelBase;

public class MainView extends BorderPaneWithBottomPanelBase {

    private final BooleanProperty playing = new SimpleBooleanProperty(false);
    GraphViewPane graphViewPane;

    public MainView() {

        setId("mainRoot");

        //center - graph
        graphViewPane = new GraphViewPane();
        setCenter(graphViewPane);

        //settingsButton
        SettingsButton settingsButton = new SettingsButton();
        StackPane topRight = new StackPane(settingsButton);
        StackPane.setAlignment(settingsButton, javafx.geometry.Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(20));
        setTop(topRight);
        
        /*
        LikeButton likeButton = new LikeButton();
        VolumeButton volumeButton = new VolumeButton();

        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(20));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        bottomBar.getChildren().addAll(
                likeButton,   
                spacer,       
                volumeButton  
        );

        setBottom(bottomBar);

        playing.addListener((obs, oldV, newV) -> {
        setBottom(newV ? graphControl : bottomBar);
        System.out.println("." + newV);
    }); */
    }

    public BooleanProperty playingProperty() {
        return playing;
    }
}
