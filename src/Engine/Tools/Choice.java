package Engine.Tools;

import Engine.Windows.TextMainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static Engine.Tools.Statics.abstractMainWindow;

/**
 * A choice is the option for a button.
 * Once the button is pressed, it will execute the choice.
 * The choice will then add new text to the textMainWindow and clear the text if necessary.
 */
public class Choice {

    private EventHandler<ActionEvent> event;

    private String name;
    private String description;

    /**
     * @param clear True if the previous text needs to be cleared
     * @param text The text that will be displayed
     * @param name The name of the choice, will be on the button
     * @param description A short description of the choice
     */
    public Choice(boolean clear, String text, String name, String description) {
        TextMainWindow textMainWindow = (TextMainWindow) abstractMainWindow;
        this.name = name;
        this.description = description;
        this.event = event -> {
            if (clear) {
                textMainWindow.clear();
            } else {
                textMainWindow.addText("\n");
            }
            textMainWindow.addText(text);
        };
    }

    public EventHandler<ActionEvent> getEvent() {
        return event;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
