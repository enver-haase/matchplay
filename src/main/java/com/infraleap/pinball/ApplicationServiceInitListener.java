package com.infraleap.pinball;

import com.infraleap.pinball.event.TimerEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

public class ApplicationServiceInitListener
        implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener( evt -> {
            new Thread( () -> {
                UI ui = evt.getUI();

                while (evt.getSource().isUIActive(ui)) {
                    try {
                        Thread.sleep(4000);
                        ComponentUtil.fireEvent(ui, new TimerEvent(ui));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } );
    }

}
