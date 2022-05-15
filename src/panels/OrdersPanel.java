package panels;

import base.BasePanel;
import customUI.BarButton;
import database.Database;
import frames.MainFrame;
import models.Category;
import models.Order;
import models.Product;
import models.ProductType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class OrdersPanel extends BasePanel {
    public int tableNumber;
    //orders table
    public JTable ordersTable;
    public DefaultTableModel ordersTableModel;

    //products table
    public JTable productsTable;
    public DefaultTableModel productsTableModel;

    public ArrayList<Product> products;
    public Order selectedOrder;
    public Product selectedProduct;
    public int currentlySelectedProductRow;
    public ArrayList<JButton> categoriesButtons;
    public ArrayList<JButton> productsButtons;
    public JLabel backgroundLabel;

    public OrdersPanel(MainFrame frame, int tableNumber) {
        super(frame);
        this.tableNumber = tableNumber;
        products = Database.getProducts();
        /////
        backgroundLabel = new JLabel(new ImageIcon("horizontTable.jpg"), JLabel.CENTER);
        backgroundLabel.setBounds(0,0, 920, 590);
        add(backgroundLabel);

        createHeader();
        createOrderButtons();
        createOrdersTable();
        createProductsTable();
        createCategoriesButtons();
        createSettingsButton();
        refreshOrders();
    }
    public void refreshOrders() {
        frame.dataProvider.refreshOrdersTable(ordersTableModel, this.tableNumber);
    }
    public void loadProducts() {
        frame.dataProvider.loadProductsInTable(productsTableModel, this.selectedOrder);
    }
    public void createCategoriesButtons() {
        categoriesButtons = new ArrayList<>();
        int buttonX = 260;
        int buttonY = 0;

        for(int i = 0; i < frame.dataProvider.categories.size(); i++) {
            Category category = frame.dataProvider.categories.get(i);
            buttonY += 55;

            BarButton productButton = new BarButton(category.title);
            productButton.setBounds(buttonX,buttonY,280,50);
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeAllButtons();
                    createProductButtons(category.type);
                    repaint();
                }
            });
            categoriesButtons.add(productButton);
            backgroundLabel.add(productButton);
            //add(productButton);

            ////
            //backgroundLabel = new JLabel(new ImageIcon("horizontTable.jpg"), JLabel.CENTER);
            //backgroundLabel.setBounds(0,0, 920, 590);
            //add(backgroundLabel);

        }
    }
    public void createProductButtons(ProductType type) {
        productsButtons = new ArrayList<>();
        int buttonX = 260;
        int buttonY = 0;

        for(int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getType() != type) {
                continue;
            }
            buttonY += 55;

            BarButton productButton = new BarButton(product.getBrand());
            productButton.product = product;
            productButton.setBounds(buttonX,buttonY,280,50);
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(selectedOrder != null) {
                        boolean isProductFound = false;
                        for(Product product : selectedOrder.getProducts()) {
                            if(product.getUid().equals(((BarButton)e.getSource()).product.getUid())) {
                                product.increaseCount();
                                isProductFound = true;
                                break;
                            }
                        }
                        if(!isProductFound) {
                            selectedOrder.getProducts().add(((BarButton)e.getSource()).product);
                        }
                        refreshOrders();
                        loadProducts();
                    } else {
                        showError("Нямате поръчка за тази маса!");
                    }
                }
            });
            productsButtons.add(productButton);

            backgroundLabel.add(productButton);
            //add(productButton);
        }
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllButtons();
                createCategoriesButtons();
                repaint();
            }
        });
        backButton.setBounds(buttonX,buttonY+55,280,50);
        productsButtons.add(backButton);
        backgroundLabel.add(backButton);
        //add(backButton);

    }
    public void createHeader() {
        JLabel tableNumberLabel = new JLabel("Маса: " + this.tableNumber);
        tableNumberLabel.setBounds(frame.getWidth() / 2-50, 10, 100, 40);
        //backgroundLabel.add(tableNumberLabel);
        add(tableNumberLabel);
    }

    public void createProductsTable() {
        String columns[] = {"Продукт", "Количество", "Цена"};
        productsTableModel = new DefaultTableModel();
        productsTableModel.setColumnIdentifiers(columns);
        //250  300  250
        productsTable = new JTable(productsTableModel);
        productsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currentlySelectedProductRow = productsTable.getSelectedRow();
                selectedProduct = selectedOrder.getProducts().get(productsTable.getSelectedRow());
            }
        });

        JScrollPane pane = new JScrollPane(productsTable);
        pane.setBounds(frame.getWidth()-250, 50, 250, 450);
        backgroundLabel.add(pane);
        //add(pane);
    }
    public void createSettingsButton() {
        JButton increaseButton = new JButton("+");
        increaseButton.setBounds(frame.getWidth()-250, 504, 44,44);
        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProduct(true);
            }
        });
        backgroundLabel.add(increaseButton);
        //add(increaseButton);

        JButton decreaseButton = new JButton("-");
        decreaseButton.setBounds(frame.getWidth()-200, 504, 44,44);
        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProduct(false);
            }
        });
        backgroundLabel.add(decreaseButton);
        //add(decreaseButton);
    }
    public void modifyProduct(boolean isInreasing) {
        if(selectedProduct == null) {
            showError("Трябва да селектираш продукт");
            return;
        }
        if(isInreasing) {
            selectedProduct.increaseCount();
        } else {
            if(selectedProduct.getCount() == 1) {
                selectedOrder.getProducts().remove(selectedProduct);
            } else {
                selectedProduct.decreaseCount();
            }
        }
        refreshOrders();
        loadProducts();
        if(currentlySelectedProductRow < selectedOrder.getProducts().size()) {
            productsTable.setRowSelectionInterval(currentlySelectedProductRow, currentlySelectedProductRow);
        }
    }
    public void createOrdersTable() {
        String columns[] = {"Номер", "Продукти", "Цена"};
        ordersTableModel = new DefaultTableModel();
        ordersTableModel.setColumnIdentifiers(columns);
        //250  300  250
        ordersTable = new JTable(ordersTableModel);
        ordersTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ArrayList<Order> tableOrders = new ArrayList<>();
                for(Order order : frame.dataProvider.orders) {
                    if(order.getTableNumber() == tableNumber) {
                        tableOrders.add(order);
                    }
                }
                selectedOrder = tableOrders.get(ordersTable.getSelectedRow());
                loadProducts();
            }
        });
        JScrollPane pane = new JScrollPane(ordersTable);
        pane.setBounds(0, 50, 250, 500);
        backgroundLabel.add(pane);
        //add(pane);
    }

    public void createOrderButtons() {
        JButton createOrderButton = new JButton("Създай");
        createOrderButton.setBounds(0,0,120, 44);
        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrderAction();
            }
        });
        backgroundLabel.add(createOrderButton);
        //add(createOrderButton);

        JButton finishOrderButton = new JButton("Приключи");
        finishOrderButton.setBounds(130,0,120, 44);
        finishOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedOrder == null) {
                    showError("Няма избрана поръчка!");
                    return;
                }
                int confirm = showQuestionPopup("Завършване на поръчката?");
                if(confirm == JOptionPane.YES_OPTION) {
                    frame.dataProvider.orders.remove(selectedOrder);
                    frame.router.showLoginPanel();
                }
            }
        });
        backgroundLabel.add(finishOrderButton);
        //add(finishOrderButton);

        JButton cancelButton = new JButton("Отказ");
        cancelButton.setBounds(0,506,250,44);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.router.showLoginPanel();
            }
        });
        backgroundLabel.add(cancelButton);
        //add(cancelButton);
    }
    public void createOrderAction() {
        int result = showQuestionPopup("Сигурни ли сте, че искате да създадете нова поръчка?");
        if(result == JOptionPane.YES_OPTION) {
            String uid = Integer.toString(frame.dataProvider.orders.size() + 1);
            Order order = new Order(uid, this.tableNumber, new ArrayList<>());
            frame.dataProvider.orders.add(order);
            refreshOrders();
        }
    }

    public void removeAllButtons() {
        //Remove categories buttons from Panel
        if(categoriesButtons != null) {
            for (JButton button : categoriesButtons) {
                remove(button);
            }
        }
        //Remove product buttons from Panel
        if(productsButtons != null) {
            for (JButton button : productsButtons) {
                remove(button);
            }
        }
    }
}
