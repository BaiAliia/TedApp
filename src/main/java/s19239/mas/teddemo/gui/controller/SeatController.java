package s19239.mas.teddemo.gui.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import s19239.mas.teddemo.gui.view.SeatView;
import s19239.mas.teddemo.model.ConferenceHall;
import s19239.mas.teddemo.model.Seat;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.services.SeatServices;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SeatController {
    private final SeatView view;
    private final SeatServices seatService;


    public void showGUI(MainWindowController mainController, Session sess) {
        updateSeatsFromDB(sess);
        mainController.showView(view.getSeatpanel());
    }

    private void updateSeatsFromDB(Session sess) {
        List<Seat> seats = seatService.getAvailableSeatBySession(sess);
        if (seats.size() == 0) {
            JOptionPane.showMessageDialog(view.getSeatpanel(), "No available seats for selected session of the conference!");
        }
        DefaultListModel<Seat> model = (DefaultListModel<Seat>) view.getSeatList().getModel();
        model.removeAllElements();
        seats.forEach(model::addElement);
    }

    @PostConstruct
    public void initListeners() {
        view.getSeatList().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Seat selectedValue = (Seat) view.getSeatList().getSelectedValue();
                System.out.println("Seat selected: " + Integer.toString(selectedValue.getSeatNumber()));

            }
        });
    }

}
