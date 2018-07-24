package Engine;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Choice {

    private EventHandler<ActionEvent> event;

    String text;

    // TODO determine how the choices will be handled
    Choice(boolean clear) {
        this.event = event -> {

        };
    }

    public EventHandler<ActionEvent> getEvent() {
        return event;
    }
}
