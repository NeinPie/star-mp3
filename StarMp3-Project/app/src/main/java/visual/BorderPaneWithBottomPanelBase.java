package visual;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public abstract class BorderPaneWithBottomPanelBase extends BorderPane {
    public void setBottomPanel(Pane bottomPanel){
        setBottom(bottomPanel);
    }
}
