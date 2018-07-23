package Engine.Windows;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SideBar extends Pane implements Window {

    Button showHide;
    boolean visibility;
    Background hideButton;
    Background showButton;
    final double WIDTH = 100;
    final double BUTTONSIZE = 40;

    SideBar(Backfield field, String hidePath, String showPath) {
        showHide = new Button();

        try {
            Image hideButtonImage = new Image(hidePath);
            Image showButtonImage = new Image(showPath);

            BackgroundImage show = new BackgroundImage(hideButtonImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            BackgroundImage hide = new BackgroundImage(hideButtonImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

            hideButton = new Background(hide);
            showButton = new Background(show);
        } catch (IllegalArgumentException ex) {
            hideButton = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
            showButton = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
        }

        showHide.setBackground(hideButton);

        showHide.setOnAction(event -> {
            if (visibility) {
                hide();
            } else {
                show(field);
            }
        });

        showHide.setPrefSize(BUTTONSIZE,BUTTONSIZE);

        getChildren().add(showHide);

        showHide.fire();
    }

    void hide() {
        showHide.setBackground(showButton);
        visibility = false;
        setMinWidth(BUTTONSIZE);
        prefWidthProperty().unbind();
        prefWidthProperty().set(BUTTONSIZE);
    }

    void show(Backfield field) {
        showHide.setBackground(hideButton);
        visibility = true;
        setMinWidth(WIDTH);
        prefWidthProperty().bind(field.widthProperty().divide(8));
        showHide.layoutXProperty().bind(this.widthProperty().subtract(BUTTONSIZE));
    }

    @Override
    public void setLocation(Backfield field) {
        setLayoutX(0);
        setLayoutY(0);
        setMinWidth(WIDTH);
        layoutXProperty().bind(field.layoutXProperty());
        layoutYProperty().bind(field.layoutYProperty());
        prefWidthProperty().bind(field.widthProperty().divide(8));
        prefHeightProperty().bind(field.heightProperty());
    }
}
