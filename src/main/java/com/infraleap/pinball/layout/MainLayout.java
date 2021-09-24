package com.infraleap.pinball.layout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.shared.communication.PushMode;

@Push(PushMode.MANUAL)
public class MainLayout extends Div implements RouterLayout {
}
