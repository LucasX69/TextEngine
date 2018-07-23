package Engine.Windows;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ControlWindow extends Pane implements Window {

    private HBox row;
    private List<Button> listRow;

    ControlWindow() {
        // field.getChilds().get(1) = Side

        listRow = new ArrayList<>();
        row = new HBox();
        for (int i = 0; i < 5; i++) {
            Button button = new Button();
            row.getChildren().add(button);
            listRow.add(button);
        }

        // Add the row
        getChildren().add(row);
    }

    public List<Button> getListRow() {
        return listRow;
    }

    @Override
    public void setLocation(Backfield field) {
        layoutXProperty().bind(field.getChilds().get(1).widthProperty());
        layoutYProperty().bind(field.heightProperty().subtract(field.heightProperty().divide(7)));
        prefHeightProperty().bind(field.heightProperty().divide(7));
        prefWidthProperty().bind(field.widthProperty().subtract(field.getChilds().get(1).widthProperty()));
    }
}
