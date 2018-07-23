package Engine.Windows;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ControlWindow extends Pane {

    private HBox row;
    private List<Button> listRow;

    ControlWindow(Backfield field, SideBar side) {
        listRow = new ArrayList<>();
        row = new HBox();
        for (int i = 0; i < 5; i++) {
            Button button = new Button();
            row.getChildren().add(button);
            listRow.add(button);
        }

        // Add the row
        getChildren().add(row);

        // Set the location
        layoutXProperty().bind(side.widthProperty());
        layoutYProperty().bind(field.heightProperty().subtract(field.heightProperty().divide(7)));
        prefHeightProperty().bind(field.heightProperty().divide(7));
        prefWidthProperty().bind(field.widthProperty().subtract(side.widthProperty()));
    }

    public List<Button> getListRow() {
        return listRow;
    }
}
