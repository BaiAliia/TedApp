package s19239.mas.teddemo.gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Component
@Data
public class MainWindowView extends JFrame {

    private JButton buyATicket;
    private MenuPane mainPanel;

    public MainWindowView() {
        setTitle("Conference ticket buying app");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new MenuPane();
        this.add(mainPanel);
    }


    public class MenuPane extends JPanel {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            JPanel menu = new JPanel(new FlowLayout());
            menu.add(new JButton("My profile"));
            menu.add(new JButton("My purchases"));
            menu.add(new JButton("Customer Support"));
            menu.add(new JButton("Log out"));

            add(menu, gbc);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(new JButton("Facility"), gbc);
            buttons.add(new JButton("Conferences"), gbc);
            buyATicket = new JButton("Buy a ticket");
            buttons.add(buyATicket, gbc);

            gbc.weighty = 1;
            add(buttons, gbc);

        }
    }
}
