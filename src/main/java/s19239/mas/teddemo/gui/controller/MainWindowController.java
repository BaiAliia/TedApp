package s19239.mas.teddemo.gui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import s19239.mas.teddemo.gui.view.*;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Seat;
import s19239.mas.teddemo.model.Session;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Controller
@RequiredArgsConstructor
public class MainWindowController {
    private final MainWindowView view;

    private final ConferenceViewController conferenceController;
    private final ConferenceView conferenceView;

    private final SessionController sessionController;
    private final SessionView sessionView;

    private final SeatController seatListWindowController;
    private final SeatView seatListView;
    private final ConfirmationController confirmationController;
    private final ConfirmationView confirmationView;


    public void showGUI() {
        view.setVisible(true);
    }

    @PostConstruct
    private void initPanelListeners() {
        view.getBuyATicket().addActionListener(e -> {
            conferenceController.showGUI(this);
        });
        conferenceView.getNextButton().addActionListener(e -> {
            Conference c = (Conference) conferenceView.getConferenceList().getSelectedValue();
            sessionController.showGUI(this, c);
        });
        sessionView.getButton1().addActionListener(e -> {
            Session s = (Session) sessionView.getSessionList().getSelectedValue();
            seatListWindowController.showGUI(this, s);
        });
        seatListView.getNextButton().addActionListener(e -> {
            Seat seat = (Seat) seatListView.getSeatList().getSelectedValue();
            Session session = (Session) sessionView.getSessionList().getSelectedValue();
            confirmationController.showGUI(this, seat, session);
        });
        confirmationView.getCancelButton().addActionListener(e -> {

           showView(view.getMainPanel());
        });

    }


    public void showView(JPanel viewToShow) {
        view.getContentPane().removeAll();
        view.setContentPane(viewToShow);
        view.revalidate();
    }


}
