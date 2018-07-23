package Engine.Windows;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ControlWindow extends Pane implements Window {

    private VBox vBox;
    private HBox row;
    private HBox row2;
    private HBox row3;
    private List<Button> listRow;
    private List<Button> listRow2;
    private List<Button> listRow3;

    ControlWindow() {
        // field.getChilds().get(1) = Side
        // TODO give the buttons keystrokes.

        listRow = new ArrayList<>();
        listRow2 = new ArrayList<>();
        listRow3 = new ArrayList<>();
        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();
        vBox = new VBox(row, row2, row3);
        for (int i = 0; i < 6; i++) {
            Button button = new Button();
            Button button2 = new Button();
            Button button3 = new Button();
            row.getChildren().add(button);
            row2.getChildren().add(button2);
            row3.getChildren().add(button3);
            listRow.add(button);
            listRow2.add(button2);
            listRow3.add(button3);
        }

        // Add the vbox
        getChildren().addAll(vBox);
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

        for (int i = 0; i < listRow.size(); i++) {
            listRow.get(i).prefWidthProperty().bind(this.widthProperty().divide(listRow.size()).subtract(row.getSpacing()));
            listRow.get(i).prefHeightProperty().bind(this.heightProperty().divide(vBox.getChildren().size()).subtract(vBox.getSpacing()));
            listRow2.get(i).prefWidthProperty().bind(this.widthProperty().divide(listRow2.size()).subtract(row2.getSpacing()));
            listRow2.get(i).prefHeightProperty().bind(this.heightProperty().divide(vBox.getChildren().size()).subtract(vBox.getSpacing()));
            listRow3.get(i).prefWidthProperty().bind(this.widthProperty().divide(listRow3.size()).subtract(row3.getSpacing()));
            listRow3.get(i).prefHeightProperty().bind(this.heightProperty().divide(vBox.getChildren().size()).subtract(vBox.getSpacing()));
        }
    }
}
