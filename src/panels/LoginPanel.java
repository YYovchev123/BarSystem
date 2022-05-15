package panels;

import base.BasePanel;
import frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends BasePanel {
    public JTextField pinField;
    public JLabel background;


    public LoginPanel(MainFrame frame) {
        super(frame);

        background = new JLabel(new ImageIcon("VarnaHorizont.PNG"), JLabel.CENTER);
        background.setBounds(0,0, 920, 590);
        add(background);

        JLabel loginLabel = new JLabel("Добре дошли в Advance Bar");
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setBounds(frame.getWidth() / 2 - 160, 80, 320, 40);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Serif", Font.BOLD, 20));
        background.add(loginLabel);



        pinField = new JTextField("Въведете пин");
        pinField.setBounds(frame.getWidth() / 2 - 50, 130, 100, 40);
        pinField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pinField.setText("");
            }
        });
        pinField.setHorizontalAlignment(SwingConstants.CENTER);
        background.add(pinField);

        JButton loginButton = new JButton("Влез");
        loginButton.setBackground(new Color(5, 110, 190));
        loginButton.setBounds(frame.getWidth() / 2 - 50, 175, 100, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frame.dataProvider.isPinCorrect(pinField.getText())) {
                    frame.router.showTablesPanel();
                } else {
                    showError("Грешен пин, опитайте пак!");
                }
            }
        });
        background.add(loginButton);

        JLabel clockLabel = clockLabel();
        clockLabel.setForeground(Color.ORANGE);
        background.add(clockLabel);

        ImageIcon imageIcon = new ImageIcon("coctail.jpg");

        JButton infoButton = new JButton("Повече информация");
        infoButton.setBounds(10, 510, 170, 40);
        infoButton.setBackground(new Color(20, 140, 80));
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInfo("Advance bar е разположен на едно\n" +
                                "от най-красивите места във Варна!\n" +
                                "В него може да се насладите на\n" +
                                "гледката докато пиете своята супер\n" +
                                "вкусна напитка!"
                , "Повече информация за Advance Bar", imageIcon);
            }
        });
        background.add(infoButton);


    }
}
