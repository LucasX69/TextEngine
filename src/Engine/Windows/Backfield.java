package Engine.Windows;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Backfield extends Pane {

    public List<Node> children;

    public Backfield(String showButtonPath, String hideButtonPath) {
        children = new ArrayList<>();

        SideBar side = new SideBar(this, hideButtonPath, showButtonPath);
        ControlWindow bottom = new ControlWindow(this, side);
        AbstractWindow main = new TextWindow(this, side);

        side.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        bottom.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        main.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));


        children.add(side);
        children.add(bottom);
        children.add(main);

        getChildren().addAll(side, bottom, main);
    }

}
