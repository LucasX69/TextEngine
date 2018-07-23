package Engine.Windows;

import javafx.scene.control.TextArea;

public class TextWindow extends AbstractWindow {

    private TextArea text;

    public TextWindow(Backfield field, SideBar side) {
        // TODO make text appear correctly
        super(field, side);
        text = new TextArea();
        text.setEditable(false);
        getChildren().add(text);
    }

    public void addText(String s) {
        if (!text.getText().isEmpty()) text.appendText("\n");
        text.appendText(s);
    }

}
