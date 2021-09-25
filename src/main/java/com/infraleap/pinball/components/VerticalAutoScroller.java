package com.infraleap.pinball.components;

import com.infraleap.pinball.event.TimerEvent;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

public class VerticalAutoScroller extends Scroller /*implements HasComponents*/ {
    // TODO: for a release, make it implement HasComponents, likely override all default methods

    private volatile int currentlyVisible = 0;

    private final VerticalLayout verticalLayout = new VerticalLayout();

    public VerticalAutoScroller(){
        setScrollDirection(ScrollDirection.VERTICAL);
        super.setContent(verticalLayout);
    }

    private Registration timerRegistration;

    @Override
    protected void onAttach(AttachEvent attachEvent){
        timerRegistration = ComponentUtil.addListener(attachEvent.getUI(), TimerEvent.class, e->{
            if (verticalLayout.getChildren().findAny().isPresent()) {
                //System.out.println("tick! " + currentlyVisible);
                synchronized (this) {
                    ++currentlyVisible;
                    currentlyVisible %= verticalLayout.getChildren().count();
                }
                attachEvent.getUI().access(() -> {
                    verticalLayout.getComponentAt(currentlyVisible).getElement().executeJs("$0.scrollIntoView();");
                    attachEvent.getUI().push();
                });
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        timerRegistration.remove();
    }

    @Override
    public void setContent(Component component){
        throw new UnsupportedOperationException();
    }

    public void add(Component... components){
        this.verticalLayout.add(components);
    }

    public void addEmptyLine(){
        this.verticalLayout.add("");
    }
}
