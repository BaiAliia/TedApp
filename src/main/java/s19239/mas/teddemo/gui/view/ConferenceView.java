package s19239.mas.teddemo.gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import s19239.mas.teddemo.model.Conference;

import javax.swing.*;

@Component
@Data
public class ConferenceView {

    private JPanel mainPanel;
    private JList ConferenceList;
    private JLabel SelectedConferenceValue;
    private JButton nextButton;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        ConferenceList = new JList<Conference>();
        ConferenceList.setCellRenderer(new ConferenceListCellRenderer());
        ConferenceList.setModel(new DefaultListModel<Conference>());
    }
    private class ConferenceListCellRenderer extends JLabel implements ListCellRenderer<Conference> {

        public ConferenceListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Conference> list, Conference value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getTitle());
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
