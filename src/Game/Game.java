package Game;

import Engine.Windows.Backfield;
import Engine.Windows.TextWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String imageDir = "/Game/Images/";
        String hideButton = imageDir + "hideButton.jpg";
        String showButton = imageDir + "showButton.jpg";

        Backfield root = new Backfield(hideButton, showButton);

        Scene scene =  new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Test Game");
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        TextWindow x = (TextWindow)root.children.get(2);
        x.addText("Hello");
        x.addText("Hello");
        x.addText("Hello");
        x.addText("Hello");
    }
}
