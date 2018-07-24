package Engine.Windows;

import Engine.Choice;
import javafx.scene.Scene;
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
        listRow = new ArrayList<>();
        listRow2 = new ArrayList<>();
        listRow3 = new ArrayList<>();
        row = new HBox();
        row2 = new HBox();
        row3 = new HBox();
        vBox = new VBox(row, row2, row3);

        createButtons();

        // Add the vbox
        getChildren().addAll(vBox);
    }

    void keyStrokes(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                // Row 1
                case Q: listRow.get(0).fire(); break;
                case W: listRow.get(1).fire(); break;
                case E: listRow.get(2).fire(); break;
                case R: listRow.get(3).fire(); break;
                case T: listRow.get(4).fire(); break;
                case Y: listRow.get(5).fire(); break;
                // Row 2
                case A: listRow2.get(0).fire(); break;
                case S: listRow2.get(1).fire(); break;
                case D: listRow2.get(2).fire(); break;
                case F: listRow2.get(3).fire(); break;
                case G: listRow2.get(4).fire(); break;
                case H: listRow2.get(5).fire(); break;
                // Row 3
                case Z: listRow3.get(0).fire(); break;
                case X: listRow3.get(1).fire(); break;
                case C: listRow3.get(2).fire(); break;
                case V: listRow3.get(3).fire(); break;
                case B: listRow3.get(4).fire(); break;
                case N: listRow3.get(5).fire(); break;
            }
        });
    }

    /**
     * Clears the buttons
     */
    public void buttonClear() {
        // Clear the buttons
        for (int i = 0; i < listRow.size(); i++) {
            listRow.get(i).setOnAction(null);
            listRow2.get(i).setOnAction(null);
            listRow3.get(i).setOnAction(null);
        }
    }

    /**
     * Tells the button which Choice it needs to use when the button is pressed
     * @param choice The new event that will happen
     * @param button The button that needs the Choice
     */
    public void setButton(Choice choice, Button button) {
        button.setOnAction(choice.getEvent());
    }

    @Override
    public void setLocation(Backfield field) {
        layoutXProperty().bind(field.getListChildren().get(1).widthProperty());
        layoutYProperty().bind(field.heightProperty().subtract(field.heightProperty().divide(7)));
        prefHeightProperty().bind(field.heightProperty().divide(7));
        prefWidthProperty().bind(field.widthProperty().subtract(field.getListChildren().get(1).widthProperty()));

        for (int i = 0; i < listRow.size(); i++) {
            listRow.get(i).prefWidthProperty().bind(this.widthProperty().divide(listRow.size()).subtract(row.getSpacing()));
            listRow.get(i).prefHeightProperty().bind(this.heightProperty().divide(vBox.getChildren().size()).subtract(vBox.getSpacing()));
            listRow2.get(i).prefWidthProperty().bind(this.widthProperty().divide(listRow2.size()).subtract(row2.getSpacing()));
            listRow2.get(i).prefHeightProperty().bind(this.heightProperty().divide(vBox.getChildren().size()).subtract(vBox.getSpacing()));
            listRow3.get(i).prefWidthProperty().bind(this.widthProperty().divide(listRow3.size()).subtract(row3.getSpacing()));
            listRow3.get(i).prefHeightProperty().bind(this.heightProperty().divide(vBox.getChildren().size()).subtract(vBox.getSpacing()));
        }
    }

    /**
     * A helper method that creates the buttons and adds them to the Window
     */
    private void createButtons() {
        listRow.add(new Button("Q"));
        listRow.add(new Button("W"));
        listRow.add(new Button("E"));
        listRow.add(new Button("R"));
        listRow.add(new Button("T"));
        listRow.add(new Button("Y"));

        listRow2.add(new Button("A"));
        listRow2.add(new Button("S"));
        listRow2.add(new Button("D"));
        listRow2.add(new Button("F"));
        listRow2.add(new Button("G"));
        listRow2.add(new Button("H"));

        listRow3.add(new Button("Z"));
        listRow3.add(new Button("X"));
        listRow3.add(new Button("C"));
        listRow3.add(new Button("V"));
        listRow3.add(new Button("B"));
        listRow3.add(new Button("N"));

        row.getChildren().addAll(listRow);
        row2.getChildren().addAll(listRow2);
        row3.getChildren().addAll(listRow3);
    }
}
