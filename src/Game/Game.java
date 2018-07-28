package Game;

import Engine.Tools.Handlers.StoryHandler;
import Engine.Tools.Statics;
import Engine.Windows.Backfield;
import Engine.Windows.TextMainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
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

        root.assignKeys(scene);

        // TODO Hard coded for now, need to figure out how to make it actually find the files.
        try {
            StoryHandler storyHandler = new StoryHandler("/Users/lucas/Documents/GitHub/TextEngine/src/Game/Story/Introduction.tes");
            ((TextMainWindow)Statics.abstractMainWindow).addText(storyHandler.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
