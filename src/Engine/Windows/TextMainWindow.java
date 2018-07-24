package Engine.Windows;

import javafx.scene.control.TextArea;

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
    public void setLocation(Backfield field) {
        super.setLocation(field);
        text.prefWidthProperty().bind(field.widthProperty().subtract(field.getListChildren().get(1).widthProperty()));
        text.prefHeightProperty().bind(field.heightProperty().subtract(field.getListChildren().get(0).heightProperty()));
    }
}
