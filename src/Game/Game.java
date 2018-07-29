package Game;

import Engine.Characters.Character;
import Engine.Enums.Occupations.Job;
import Engine.Enums.People.Gender;
import Engine.Enums.People.Type;
import Engine.Tools.Handlers.StoryHandler;
import Engine.Windows.Backfield;
import Engine.Windows.TextMainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

import static Engine.Tools.Statics.abstractMainWindow;
import static Engine.Tools.Statics.characters;

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

        characters = new ArrayList<>();
        characters.add(new Character("Jason", 18, Gender.MALE, "Nothing","Las Vegas", Job.JOBLESS, Type.PLAYER));

        // TODO Hard coded for now, need to figure out how to make it actually find the files.
        try {
            StoryHandler storyHandler = new StoryHandler("/Users/lucas/Documents/GitHub/TextEngine/src/Game/Story/Introduction.tes");
            ((TextMainWindow)abstractMainWindow).addText(storyHandler.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
