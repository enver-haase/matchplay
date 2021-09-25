package com.infraleap.pinball.views.chooser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.config.Config;
import com.infraleap.pinball.data.matchplay.Tournament;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@PageTitle("Tournament Selector")
@Route(value = "tournament-select", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@SpringComponent
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TournamentSelectView extends VerticalLayout {

    @Value("classpath:data/config.json")
    private Resource resourceFile;

    private final MatchPlayService matchPlayService;

    public TournamentSelectView(MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassName("hello-world-view");
    }

    @PostConstruct
    private void init(){
        Config config = readConfiguration();
        List<List<String>> tourneySets = config.getTournamentSets();

        VerticalAutoScroller vas = new VerticalAutoScroller();
        add(vas);

        for (List<String> tourneySet : tourneySets){
            VerticalLayout vl = new VerticalLayout();
            vl.setBoxSizing(BoxSizing.BORDER_BOX);
            for (String tourney : tourneySet){
                Tournament tournament = matchPlayService.getTournament(tourney);
                vl.add(new Span(tournament.getName()));
            }
            vas.add(vl);
        }
    }


    private Config readConfiguration(){
        Config config;
        try {
            ObjectMapper mapper = new ObjectMapper();
            config = mapper.readValue(resourceFile.getFile(), Config.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return config;
    }
}
