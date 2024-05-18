package com.infraleap.pinball.layout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;


@PageTitle(MainLayout.NAME_LONG)
public class MainLayout extends Div implements RouterLayout {
    public static final String NAME_LONG = "MatchPlay Events monitoring by infraLeap";
    public MainLayout(){
        setSizeFull();
    }
}
