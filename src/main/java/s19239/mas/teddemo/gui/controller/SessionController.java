package s19239.mas.teddemo.gui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import s19239.mas.teddemo.gui.view.SessionView;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.services.SessionService;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SessionController {
    private final SessionView view;
    private final SessionService sessionService;


    public void showGUI(MainWindowController mainController, Conference con) {
        updateSessionsFromDB(con);
        mainController.showView(view.getPanel1());
    }

    private void updateSessionsFromDB(Conference con) {
        List<Session> sessions = sessionService.findByConference(con);
        DefaultListModel<Session> model = (DefaultListModel<Session>) view.getSessionList().getModel();
        model.removeAllElements();
        sessions.forEach(model::addElement);
    }

    @PostConstruct
    public void initListeners() {
        view.getSessionList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Session selectedValue = (Session) view.getSessionList().getSelectedValue();
                System.out.println("Session selected: " + selectedValue.getStartingDateTime().toString());

            }
        });
    }
}
