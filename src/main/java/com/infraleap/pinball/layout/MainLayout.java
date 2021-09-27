package com.infraleap.pinball.layout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;

@Push(PushMode.MANUAL)
@Theme(themeFolder = "matchplay")
@PageTitle(MainLayout.NAME_LONG)
@PWA(name = MainLayout.NAME_LONG, shortName = "MatchPlay", enableInstallPrompt = false)
public class MainLayout extends Div implements RouterLayout {
    public static final String NAME_LONG = "MatchPlay Events monitoring by infraLeap";
    public MainLayout(){
        setSizeFull();
    }
}
