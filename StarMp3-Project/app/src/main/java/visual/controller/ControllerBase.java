package visual.controller;

import javafx.scene.layout.Pane;

/**
 * Vorlagenklasse für die Controller
 * @param <PaneType> Typ des Basis-Panes
 */
public abstract class ControllerBase<PaneType extends Pane> {
    PaneType root;

    public abstract void init();

    public PaneType getRoot() {
        return root;
    }
}
