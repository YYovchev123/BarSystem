package frames;

import database.Database;
import models.*;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MainDataProvider {
    public MainFrame currentFrame;

    public ArrayList<User> users;
    public ArrayList<Integer> tables;
    public ArrayList<Order> orders;
    public ArrayList<Category> categories;

    public MainDataProvider(MainFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void fetchUsers() {
        users = new ArrayList<>();
        User user1 = new User("Georgi Todorov", "0889855555", "0101", UserType.WAITRESS);
        User user2 = new User("Ivan Todorov", "08434234234", "2222", UserType.WAITRESS);
        User user3 = new User("Viktor Todorov", "066884224", "0000", UserType.MANAGER);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    public void fetchCategories() {
        this.categories = Database.getCategories();
    }
    public void fetchTables() {
        tables = new ArrayList<>();
        tables.add(1);
        tables.add(2);
        tables.add(3);
        tables.add(4);
        tables.add(5);
        tables.add(6);
        tables.add(7);
        tables.add(8);
        tables.add(9);
        tables.add(10);
    }
    public boolean isPinCorrect(String pinCode) {
        for(User user : users) {
            if(user.getPin().equals(pinCode)) {
                return true;
            }
        }
        return false;
    }

    public void refreshOrdersTable(DefaultTableModel model, int tableNumber) {
        model.setRowCount(0);
        for(Order order : this.orders) {
            if(order.getTableNumber() == tableNumber) {
                String row[] = new String[3];
                row[0] = order.getUid();
                row[1] = order.getProductsCount();
                row[2] = order.getTotalAmountString();
                model.addRow(row);
            }
        }
    }
    public void loadProductsInTable(DefaultTableModel model, Order order) {
        model.setRowCount(0);
        for(Product product : order.getProducts()) {
            String row[] = new String[3];
            row[0] = product.getBrand();
            row[1] = product.getCountString();
            row[2] = product.getTotalPriceString();
            model.addRow(row);
        }
    }
}
