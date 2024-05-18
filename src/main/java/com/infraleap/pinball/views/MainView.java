package com.infraleap.pinball.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends Image {
	public MainView() {
		super("images/flipperstudio.png", "Flipperstudio cameras");
		setWidth("100%");
		setHeight("100%");
	}
}
