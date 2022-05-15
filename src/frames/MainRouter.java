package frames;

import panels.LoginPanel;
import panels.OrdersPanel;
import panels.TablesPanel;

public class MainRouter {
    public MainFrame currentFrame;

    public MainRouter(MainFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void showLoginPanel() {
        LoginPanel login = new LoginPanel(this.currentFrame);
        this.currentFrame.setContentPane(login);
        this.currentFrame.validate();
    }
    public void showTablesPanel() {
        TablesPanel tables = new TablesPanel(this.currentFrame);
        this.currentFrame.setContentPane(tables);
        this.currentFrame.validate();
    }
    public void showOrdersPanel(int tableNumber) {
        OrdersPanel orders = new OrdersPanel(this.currentFrame, tableNumber);
        this.currentFrame.setContentPane(orders);
        this.currentFrame.validate();
    }
}
