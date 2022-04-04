package s19239.mas.teddemo.gui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import s19239.mas.teddemo.gui.view.ConferenceView;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.services.ConferenceService;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConferenceViewController {
    private final ConferenceView view;
    private final ConferenceService conferenceService;

    public void showGUI(MainWindowController mainController) {
        updateConferenceFromDB();
        mainController.showView(view.getMainPanel());
    }

    @PostConstruct
    public void initListeners() {
        view.getConferenceList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Conference selectedValue = (Conference) view.getConferenceList().getSelectedValue();
                System.out.println("Conferences selected: " + selectedValue.getTitle());
                view.getSelectedConferenceValue().setText(selectedValue.getTitle());
            }
        });
    }

    private void updateConferenceFromDB() {
        List<Conference> conference = conferenceService.getAllConferences();
        DefaultListModel<Conference> model = (DefaultListModel<Conference>) view.getConferenceList().getModel();
        model.removeAllElements();
        conference.forEach(model::addElement);
    }

}
