package s19239.mas.teddemo.gui.view;

import lombok.Data;
import org.springframework.stereotype.Component;
import s19239.mas.teddemo.model.Seat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
@Data
public class SeatView {
    private JPanel seatpanel;
    private JList seatList;
    private JButton nextButton;
    private JLabel pic;

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        seatList = new JList<Seat>();
        seatList.setCellRenderer(new SeatView.SeatListCellRenderer());
        seatList.setModel(new DefaultListModel<Seat>());
        BufferedImage myPicture = ImageIO.read(new File("C:\\Users\\User\\Desktop\\MAS Project s19239\\seatsschema.png"));
        pic = new JLabel(new ImageIcon(myPicture));
    }

    private class SeatListCellRenderer extends JLabel implements ListCellRenderer<Seat> {

        public SeatListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends Seat> list, Seat value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(Integer.toString(value.getSeatNumber()));
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
