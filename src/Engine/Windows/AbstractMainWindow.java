package Engine.Windows;

import javafx.scene.layout.Pane;

public abstract class AbstractMainWindow extends Pane implements Window {

    AbstractMainWindow() {
    }

    @Override
    public void setLocation(Backfield field) {
        SideBar side = (SideBar) field.getListChildren().get(1);
        layoutXProperty().bind(side.widthProperty());
        prefHeightProperty().bind(field.heightProperty().subtract(field.heightProperty().divide(7)));
        prefWidthProperty().bind(field.widthProperty().subtract(side.widthProperty()));
    }

}
