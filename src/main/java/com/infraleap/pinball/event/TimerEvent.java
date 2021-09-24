package com.infraleap.pinball.event;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.UI;

public class TimerEvent extends ComponentEvent<UI> {
    /**
     * Creates a new event using the given source.
     * Always originates from the server side.
     *
     * @param source     the source component
     */
    public TimerEvent(UI source) {
        super(source, false);
    }
}
