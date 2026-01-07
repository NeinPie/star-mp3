package visual.views.partials;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BottomButtonPanel extends HBox{
    private final Button nextButton;
    private final Button backButton;
    private final Button repeatButton;
    private final Button shuffleButton;


    public BottomButtonPanel() {
    getStyleClass().add("control-ui");

    backButton = new Button();
    backButton.getStyleClass().addAll("icon-button-small", "back");
    backButton.setId("back-button");

    nextButton = new Button();
    nextButton.getStyleClass().addAll("icon-button-small", "next");
    nextButton.setId("next-button");

    repeatButton = new Button();
    repeatButton.getStyleClass().addAll("icon-button-small", "repeatOFF");
    repeatButton.setId("repeat-button");

    shuffleButton = new Button();
    shuffleButton.getStyleClass().addAll("icon-button-small", "shuffleOFF");
    shuffleButton.setId("shuffle-button");

    setSpacing(10);

    setAlignment(javafx.geometry.Pos.CENTER);

    getChildren().addAll(repeatButton, backButton, nextButton, shuffleButton);

    setStyle("-fx-padding: 20;");
    }

    //Zustand
    public void setRepeatState(boolean on) {
        repeatButton.getStyleClass().removeAll("repeatON", "repeatOFF");
        repeatButton.getStyleClass().add(on ? "repeatON" : "repeatOFF");
    }

    public void setShuffleState(boolean on) {
        shuffleButton.getStyleClass().removeAll("shuffleON", "shuffleOFF");
        shuffleButton.getStyleClass().add(on ? "shuffleON" : "shuffleOFF");
    }

    //Binding

    public void bindRepeat(BooleanProperty repeatProperty) {
        repeatProperty.addListener((obs, oldV, newV) ->
                setRepeatState(newV)
        );
    }

    public void bindShuffle(BooleanProperty shuffleProperty) {
        shuffleProperty.addListener((obs, oldV, newV) ->
                setShuffleState(newV)
        );
    }

    //Getters
    public Button getRepeatButton(){ 
        return repeatButton; 
    }
    public Button getBackButton(){ 
        return backButton; 
    }
    public Button getNextButton(){ 
        return nextButton; 
    }
    public Button getShuffleButton(){
        return shuffleButton; 
    }
}
