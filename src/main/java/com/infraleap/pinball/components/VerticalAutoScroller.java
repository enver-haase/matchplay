package com.infraleap.pinball.components;

import com.infraleap.pinball.event.TimerEvent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

import java.util.NoSuchElementException;

public class VerticalAutoScroller extends Scroller /*implements HasComponents*/ {
    // TODO: for a release, make it implement HasComponents, likely override all default methods

    private volatile int currentlyVisible;
    private boolean ascendingScroll;

    private final VerticalLayout verticalLayout = new VerticalLayout();

    public VerticalAutoScroller() {
        addClassName("vertical-auto-scroller");
        setScrollDirection(ScrollDirection.VERTICAL);
        verticalLayout.setSizeFull();
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        super.setContent(verticalLayout);
    }

    private Registration timerRegistration;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        synchronized(this) {
            currentlyVisible = -1; // initially ++(-1) == 0, the first element's index
            ascendingScroll = true;
        }
        timerRegistration = ComponentUtil.addListener(attachEvent.getUI(), TimerEvent.class, e -> {
            if (verticalLayout.getChildren().findAny().isPresent()) { // at least one element must be there
                //System.out.println("tick! " + currentlyVisible);
                synchronized (this) {
                    if (ascendingScroll) {
                        ++currentlyVisible;

                        // switch to descend if at end
                        if (currentlyVisible == verticalLayout.getChildren().count() -1) {
                            ascendingScroll = false;
                        }
                    } else {
                        --currentlyVisible;

                        // switch to ascend if at beginning
                        if (currentlyVisible == 0) {
                            ascendingScroll = true;
                        }
                    }
                }
                attachEvent.getUI().accessSynchronously(() -> {
                    if (verticalLayout.getChildren().count() > currentlyVisible) {
                        verticalLayout.getComponentAt(currentlyVisible).getElement().executeJs("$0.scrollIntoView({ behavior: 'smooth', block: 'center' });");
                        try {
                            attachEvent.getUI().push();
                        }
                        catch (NoSuchElementException ignored){
                            // in case the UI disappeared
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        timerRegistration.remove();
    }

    @Override
    public void setContent(Component component) {
        throw new UnsupportedOperationException();
    }

    public void add(Component... components) {
        this.verticalLayout.add(components);
    }

    public void addEmptyLine() {
        this.verticalLayout.add("");
    }
}
