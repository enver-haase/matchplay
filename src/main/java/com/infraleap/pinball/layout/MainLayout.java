package com.infraleap.pinball.layout;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;

@Push(PushMode.MANUAL)
@Theme(themeFolder = "matchplay")
@PageTitle("Match Play Events monitoring by infraLeap")
public class MainLayout extends Div implements RouterLayout {
    public MainLayout(){
        setSizeFull();
    }
}
