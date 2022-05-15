package frames;

import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public MainDataProvider dataProvider;
    public MainRouter router;
    JLabel background;

    public MainFrame() {
        super("Advance Bar");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        dataProvider = new MainDataProvider(this);
        dataProvider.fetchUsers();
        dataProvider.fetchCategories();
        dataProvider.fetchTables();
        dataProvider.orders = new ArrayList<>();

        //First panel to be loaded on start
        router = new MainRouter(this);
        router.showLoginPanel();
    }
}
