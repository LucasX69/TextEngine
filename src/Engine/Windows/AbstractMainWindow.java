package Engine.Windows;

import javafx.scene.layout.Pane;

import static Engine.Tools.Statics.*;

public abstract class AbstractMainWindow extends Pane implements Window {

    AbstractMainWindow() {
        abstractMainWindow = this;
    }

    @Override
    public void setLocation() {
        layoutXProperty().bind(sideBar.widthProperty());
        prefHeightProperty().bind(backfield.heightProperty().subtract(backfield.heightProperty().divide(7)));
        prefWidthProperty().bind(backfield.widthProperty().subtract(sideBar.widthProperty()));
    }

}
