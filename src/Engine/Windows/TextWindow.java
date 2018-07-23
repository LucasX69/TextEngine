package Engine.Windows;

import javafx.scene.control.TextArea;

public class TextWindow extends AbstractWindow {

    private TextArea text;

    TextWindow() {
        super();
        // field.getChilds().get(0) = Bottom
        // field.getChilds().get(1) = Side
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
        text.prefWidthProperty().bind(field.widthProperty().subtract(field.getChilds().get(1).widthProperty()));
        text.prefHeightProperty().bind(field.heightProperty().subtract(field.getChilds().get(0).heightProperty()));
    }
}
