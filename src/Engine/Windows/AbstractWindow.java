package Engine.Windows;

import javafx.scene.layout.Pane;

public abstract class AbstractWindow extends Pane implements Window {

    public AbstractWindow() {
    }

    @Override
    public void setLocation(Backfield field) {
        SideBar side = (SideBar) field.getChilds().get(1);
        layoutXProperty().bind(side.widthProperty());
        prefHeightProperty().bind(field.heightProperty().subtract(field.heightProperty().divide(7)));
        prefWidthProperty().bind(field.widthProperty().subtract(side.widthProperty()));
    }

}
