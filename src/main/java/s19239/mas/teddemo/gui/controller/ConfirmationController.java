package s19239.mas.teddemo.gui.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import s19239.mas.teddemo.gui.view.ConfirmationView;
import s19239.mas.teddemo.gui.view.MainWindowView;
import s19239.mas.teddemo.model.Seat;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.model.Ticket;
import s19239.mas.teddemo.model.User;
import s19239.mas.teddemo.services.TicketService;
import s19239.mas.teddemo.services.UserService;

import javax.annotation.PostConstruct;
import javax.swing.*;

@Controller
@RequiredArgsConstructor
public class ConfirmationController {
    private final ConfirmationView view;
    private final TicketService ticketService;
    private final UserService userService;
    private Seat seat;
    private Session session;



    public void showGUI(MainWindowController mainController, Seat seat, Session session) {
        updateConfirmationPage(seat, session);
        mainController.showView(view.getConpanel());

    }

    private void updateConfirmationPage(Seat seat, Session session) {
       // view.getText().setText("Conference: " + session.getConference().getTitle() + "  Facility: " + session.getHall().getFacility().getAddress() + "\r\n" + "  Hall: " + session.getHall().getName() + "\r\n" +
      //          "  Date and time: " + session.getStartingDateTime().toString() + "\r\n   Seat number: " + Integer.toString(seat.getSeatNumber()) + "\r\n" + "  Price: " + seat.getRegularPrice());
        view.getConference().setText( session.getConference().getTitle());
        view.getFacility().setText(session.getHall().getFacility().getAddress());
        view.getHall().setText( session.getHall().getName() );
        view.getDate().setText( session.getStartingDateTime().toString());
        view.getSeat().setText(Integer.toString(seat.getSeatNumber()));
        view.getPrice().setText(Double.toString(seat.getRegularPrice()));
        this.seat = seat;
        this.session = session;
    }

    @PostConstruct
    public void initListeners() {
        view.getConfirmButton().addActionListener(e -> {
            User user = userService.findUserById(1L);
            Ticket t = ticketService.CreateTicket(user, this.session, this.seat);
            double finalprice=t.CalculateFinalPrice();
            JOptionPane.showMessageDialog(view.getConpanel(), "Thank you for using our services.Enjoy the conference .Ideas worth spreading! Final price is: "+finalprice);
        });

    }
}
