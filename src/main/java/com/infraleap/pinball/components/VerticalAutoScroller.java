package com.infraleap.pinball.components;

import com.infraleap.pinball.event.TimerEvent;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

import java.util.ArrayList;
import java.util.List;

public class VerticalAutoScroller extends Scroller {

    private List<Component> components = new ArrayList<>();
    private volatile int currentlyVisible = 0;

    public VerticalAutoScroller(){
        setScrollDirection(ScrollDirection.VERTICAL);

        for (int i=0; i<200; i++){
            components.add(new Span("Hallo Du Vogel "+i));
        }

        VerticalLayout vl = new VerticalLayout();
        vl.add(components.toArray(new Component[0]));
        setContent(vl);
    }

    private Registration reg;

    @Override
    protected void onAttach(AttachEvent attachEvent){
        reg = ComponentUtil.addListener(attachEvent.getUI(), TimerEvent.class, e->{
            System.out.println("tick! "+currentlyVisible);
            synchronized (this) {
                ++currentlyVisible;
                currentlyVisible %= components.size();
            }
            attachEvent.getUI().access( () -> {
                components.get(currentlyVisible).getElement().executeJs("$0.scrollIntoView();" );
                attachEvent.getUI().push();
            } );
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        reg.remove();
    }
}
