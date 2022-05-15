package panels;

import base.BasePanel;
import frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablesPanel extends BasePanel implements ActionListener {

    public JLabel background;

    public TablesPanel(MainFrame frame) {
        super(frame);


        background = new JLabel(new ImageIcon("horizontTables.jpg"), JLabel.CENTER);
        background.setBounds(0,0, 800, 600);
        add(background);


        int buttonX = 180;
        int buttonY = frame.getHeight() / 2 - 100;

        for(int i = 0; i < frame.dataProvider.tables.size(); i++) {
            Integer tableNumber = frame.dataProvider.tables.get(i);
            if(i == 5) {
                buttonX = 180;
                buttonY = frame.getHeight() / 2 - 40;
            }
            buttonX += 60;
            JButton tableButton = new JButton(Integer.toString(tableNumber));
            tableButton.addActionListener(this);
            tableButton.setBounds(buttonX,buttonY,50,50);
            background.add(tableButton);

            JLabel clockLabel = clockLabel();
            add(clockLabel);

            JButton backButton = new JButton("Назад");
            backButton.setBounds(10, 510, 100, 40);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.router.showLoginPanel();
                }
            });
            background.add(backButton);
        }
        JLabel tableLabel = new JLabel("Маси");
        tableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tableLabel.setForeground(Color.ORANGE);
        tableLabel.setFont(new Font("Serif", Font.BOLD, 50));
        tableLabel.setBounds(frame.getWidth() / 2 - 150, 100 , 300, 50);
        background.add(tableLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int tableNumber = Integer.parseInt(((JButton)e.getSource()).getText());
        frame.router.showOrdersPanel(tableNumber);
    }
}
