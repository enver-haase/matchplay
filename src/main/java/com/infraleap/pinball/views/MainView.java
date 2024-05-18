package com.infraleap.pinball.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends Image {
	public MainView() {
		super("images/flipperstudio.png", "Flipperstudio cameras");
		setWidth("100%");
		setHeight("100%");

		addClickListener( e -> Notification.show("Nothing here yet. Tournament history and score-tracking coming soon."));
	}
}
