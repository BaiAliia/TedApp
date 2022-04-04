package s19239.mas.teddemo.gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Session;

import javax.swing.*;
@Component
@Data
public class SessionView {
    private JPanel panel1;
    private JList sessionList;
    private JButton button1;

    private void createUIComponents() {
        sessionList = new JList<Session>();
        sessionList.setCellRenderer(new SessionView.SessionListCellRenderer());
        sessionList.setModel(new DefaultListModel<Session>());

    }


    private class SessionListCellRenderer extends JLabel implements ListCellRenderer<Session> {

        public SessionListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Session> list, Session value, int index, boolean isSelected, boolean cellHasFocus) {
            String date = value.getStartingDateTime().getYear() + "-" + value.getStartingDateTime().getMonth() + "-" + value.getStartingDateTime().getDayOfMonth();
            String time = value.getStartingDateTime().getHour() + ":" + value.getStartingDateTime().getMinute();
            setText("Facility " + value.getHall().getFacility().getName() + "  Address: " + value.getHall().getFacility().getAddress() + "    Hall: " + value.getHall().getName() + "   Date: " +
                    date + "   Time: " + time);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }
}
