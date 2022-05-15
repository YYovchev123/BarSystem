package base;

import frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePanel extends JPanel {

    public MainFrame frame;

    public BasePanel(MainFrame frame) {
        setLayout(null);
        this.frame = frame;
    }

    public void showInfo(String message, String title, ImageIcon imageIcon) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, imageIcon);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public int showQuestionPopup(String message) {
        int result = JOptionPane.showConfirmDialog(null, message, "Внимание",JOptionPane.YES_NO_OPTION);
        return result;
    }
    public String timeLabel() {
        //Метод за показване на часа
        Date currentDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy / hh:mm");
        String strDate = format.format(currentDate);
        return strDate;
    }

    public JLabel clockLabel() {
        JLabel clockLabel = new JLabel(timeLabel());
        clockLabel.setBounds( 640, 530 , 150, 40);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        add(clockLabel);
        return clockLabel;
    }
}
