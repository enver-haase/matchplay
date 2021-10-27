package com.infraleap.pinball.components;

import com.infraleap.pinball.event.TimerEvent;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.board.Row;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.shared.Registration;

import java.util.NoSuchElementException;

public class VerticalAutoScroller extends Scroller /*implements HasComponents*/ {
    // TODO: for a release, make it implement HasComponents, likely override all default methods


    /**
     * This event is fired every time the scrolling switches back from descending scroll to ascending scroll,
     * meaning the content has scrolled through forward, then backwards and is now about to re-start.
     */
    public class LoopEvent extends ComponentEvent<VerticalAutoScroller>{
        public LoopEvent(VerticalAutoScroller source) {
            super(source, false);
        }
    }



    private volatile int currentlyVisible;
    private boolean ascendingScroll;

    private final Board board = new Board();
    private Row currentRow = board.addRow();

    public VerticalAutoScroller() {
        addClassName("vertical-auto-scroller");
        setScrollDirection(ScrollDirection.VERTICAL);
        board.setSizeFull();
        super.setContent(board);
    }

    private Registration timerRegistration;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        synchronized(this) {
            currentlyVisible = 0;
            ascendingScroll = true;
        }
        timerRegistration = ComponentUtil.addListener(attachEvent.getUI(), TimerEvent.class, e -> {
            if (board.getChildren().findAny().isPresent()) { // at least one element must be there
                synchronized (this) {
                    attachEvent.getUI().accessSynchronously(() -> {
                        if (board.getChildren().count() > currentlyVisible) {
                            board.getComponentAt(currentlyVisible).getElement().executeJs("$0.scrollIntoView({ behavior: 'smooth', block: 'center' });");
                            try {
                                attachEvent.getUI().push();
                            }
                            catch (NoSuchElementException ignored){
                                // in case the UI disappeared
                            }
                        }
                    });

                    if (ascendingScroll) {
                        ++currentlyVisible;

                        // switch to descend if at end
                        if (currentlyVisible == board.getChildren().count() -1) {
                            ascendingScroll = false;
                        }
                    } else {
                        --currentlyVisible;

                        // switch to ascend if at beginning
                        if (currentlyVisible == 0) {
                            ascendingScroll = true;
                            UI.setCurrent(attachEvent.getUI()); // so that the called subscriber has the UI thread-local available even in this timer-thread we're calling from.
                            ComponentUtil.fireEvent(this, new LoopEvent(VerticalAutoScroller.this));
                        }
                    }
                }
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

    public void addRow(Component... components) {
        this.board.add(components);
        currentRow = board.addRow();
    }

    public void add(Component... components) {
        for (Component component : components){
            if (currentRow.getChildren().count() >= 3){
                currentRow = board.addRow(component);
            }
            else {
                currentRow.add(component);
            }
        }
    }
}
