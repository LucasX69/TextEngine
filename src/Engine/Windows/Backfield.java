package Engine.Windows;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Backfield extends Pane {

    private List<Region> childs;

    public Backfield(String showButtonPath, String hideButtonPath) {
        childs = new ArrayList<>();

        SideBar side = new SideBar(this, hideButtonPath, showButtonPath);
        ControlWindow bottom = new ControlWindow();
        AbstractWindow main = new TextWindow();

        side.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        bottom.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        main.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));


        childs.add(bottom);
        childs.add(side);
        childs.add(main);

        for (Region region : childs) {
            if (region instanceof Window) {
                ((Window) region).setLocation(this);
            }
        }

        getChildren().addAll(side, bottom, main);
    }

    public List<Region> getChilds() {
        return childs;
    }
}
