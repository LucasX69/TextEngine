package Engine.Windows;

import javafx.scene.control.TextArea;

import static Engine.Tools.Statics.*;

public class TextMainWindow extends AbstractMainWindow {

    private TextArea text;

    TextMainWindow() {
        super();
        text = new TextArea();
        text.setEditable(false);
        text.setWrapText(true);
        getChildren().add(text);
    }

    public void addText(String s) {
        text.appendText(s);
    }

    public void clear() {
        text.clear();
    }

    @Override
    public void setLocation() {
        super.setLocation();
        text.prefWidthProperty().bind(backfield.widthProperty().subtract(sideBar.widthProperty()));
        text.prefHeightProperty().bind(backfield.heightProperty().subtract(controlWindow.heightProperty()));
    }
}
