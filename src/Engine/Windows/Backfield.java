package Engine.Windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static Engine.Tools.Statics.backfield;

public class Backfield extends Pane {

    private List<Region> listChildren;

    public Backfield(String showButtonPath, String hideButtonPath) {
        backfield = this;

        listChildren = new ArrayList<>();

        SideBar side = new SideBar(hideButtonPath, showButtonPath);
        ControlWindow bottom = new ControlWindow();
        AbstractMainWindow main = new TextMainWindow();

        side.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        bottom.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        main.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));


        listChildren.add(bottom);
        listChildren.add(side);
        listChildren.add(main);

        for (Region region : listChildren) {
            if (region instanceof Window) {
                ((Window) region).setLocation();
            }
        }

        getChildren().addAll(side, bottom, main);

    }

    public List<Region> getListChildren() {
        return listChildren;
    }

    public void assignKeys(Scene scene) {
        ((ControlWindow) listChildren.get(0)).keyStrokes(scene);
    }
}
