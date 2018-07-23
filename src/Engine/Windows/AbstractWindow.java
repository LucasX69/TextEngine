package Engine.Windows;

import javafx.scene.layout.Pane;

public abstract class AbstractWindow extends Pane {

    public AbstractWindow(Backfield field, SideBar side) {
        layoutXProperty().bind(side.widthProperty());
        prefHeightProperty().bind(field.heightProperty().subtract(field.heightProperty().divide(7)));
        prefWidthProperty().bind(field.widthProperty().subtract(side.widthProperty()));
    }

}
