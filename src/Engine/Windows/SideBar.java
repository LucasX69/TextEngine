package Engine.Windows;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static Engine.Tools.Statics.backfield;
import static Engine.Tools.Statics.sideBar;

public class SideBar extends Pane implements Window {

    private Button showHide;
    private boolean visibility;
    private Background hideButton;
    private Background showButton;
    private final double WIDTH = 100;
    private final double BUTTONSIZE = 40;

    SideBar(String hidePath, String showPath) {
        sideBar = this;

        showHide = new Button();

        try {
            Image hideButtonImage = new Image(hidePath);
            Image showButtonImage = new Image(showPath);

            BackgroundImage show = new BackgroundImage(hideButtonImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            BackgroundImage hide = new BackgroundImage(showButtonImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

            hideButton = new Background(hide);
            showButton = new Background(show);
        } catch (IllegalArgumentException ex) {
            hideButton = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
            showButton = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
        }

        showHide.setBackground(hideButton);
        showHide.setPrefSize(BUTTONSIZE,BUTTONSIZE);

        getChildren().add(showHide);

        setAction();
    }

    private void hide() {
        showHide.setBackground(showButton);
        visibility = false;
        setMinWidth(BUTTONSIZE);
        prefWidthProperty().unbind();
        prefWidthProperty().set(BUTTONSIZE);
    }

    private void show() {
        showHide.setBackground(hideButton);
        visibility = true;
        setMinWidth(WIDTH);
        prefWidthProperty().bind(backfield.widthProperty().divide(8));
        showHide.layoutXProperty().bind(this.widthProperty().subtract(BUTTONSIZE));
    }

    private void setAction() {
        showHide.setOnAction(event -> {
            if (visibility) {
                hide();
            } else {
                show();
            }
        });
        showHide.fire();
    }

    @Override
    public void setLocation() {
        setLayoutX(0);
        setLayoutY(0);
        setMinWidth(WIDTH);
        layoutXProperty().bind(backfield.layoutXProperty());
        layoutYProperty().bind(backfield.layoutYProperty());
        prefWidthProperty().bind(backfield.widthProperty().divide(8));
        prefHeightProperty().bind(backfield.heightProperty());
    }
}
