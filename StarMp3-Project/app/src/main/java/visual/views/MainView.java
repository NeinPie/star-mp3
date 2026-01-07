package visual.views;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import visual.views.partials.*;

public class MainView extends BorderPane {

    private final BooleanProperty playing = new SimpleBooleanProperty(false);

    public MainView() {

        setId("mainRoot");

        //center - graph
        GraphViewPane graphViewPane = new GraphViewPane();
        setCenter(graphViewPane);

        //settingsButton
        SettingsButton settingsButton = new SettingsButton();
        StackPane topRight = new StackPane(settingsButton);
        StackPane.setAlignment(settingsButton, javafx.geometry.Pos.TOP_RIGHT);
        topRight.setPadding(new Insets(20));
        setTop(topRight);

        // like, volume
        LikeButton likeButton = new LikeButton();
        VolumeButton volumeButton = new VolumeButton();

        BottomButtonPanel graphControl = new BottomButtonPanel();
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
    }); 
    }

    public BooleanProperty playingProperty() {
        return playing;
    }
}
