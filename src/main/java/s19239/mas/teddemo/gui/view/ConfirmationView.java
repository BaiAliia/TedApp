package s19239.mas.teddemo.gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
@Component
@Data
public class ConfirmationView {
    private JPanel conpanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private JTextField conference;
    private JTextField facility;
    private JTextField hall;
    private JTextField date;
    private JTextField seat;
    private JTextField price;
}
