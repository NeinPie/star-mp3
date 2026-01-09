package visual.components.partials.bottompanel.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ProgressBar extends VBox {

    private final Label currentTime;
    private final Label totalTime;
    private final Slider slider;

    public ProgressBar() {
        getStyleClass().add("progress-bar");

        slider = new Slider();
        slider.getStyleClass().add("progress-slider"); 

        currentTime = new Label("00:34");
        currentTime.getStyleClass().add("time-label");

        totalTime = new Label("03:23");
        totalTime.getStyleClass().add("time-label");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        HBox labelsBox = new HBox();
        labelsBox.getStyleClass().add("labels-box");
        labelsBox.setAlignment(Pos.CENTER_LEFT);
        labelsBox.getChildren().addAll(currentTime, spacer, totalTime);

        getChildren().addAll(slider, labelsBox);
    }

    public Slider getSlider() {
        return slider;
    }

    public void setCurrentTime(String time) {
        currentTime.setText(time);
    }

    public void setTotalTime(String time) {
        totalTime.setText(time);
    }
}
