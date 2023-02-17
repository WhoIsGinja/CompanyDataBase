package woo;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Observer, Serializable{
    private static final long serialVersionUID = 201708301010L;
    
    private String id;
    private String name;
    private String address;
    private ClientType type;
    
    private int orderedSales = 0;
    private double payedSales = 0.0;
    private double points = 0;

    private ArrayList<Sale> transactions;
    
    private NotificationMethod notificationMethod = new DefaultMethod(this);
    private ArrayList<Notification> notifications = new ArrayList<Notification>(); 
    
    public Client(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;

        this.type = new NormalType(this);
        this.transactions = new ArrayList<Sale>();
    }

    public String getId() { return this.id; }
    public double getPoints() { return this.points; }
    public ArrayList<Sale> getTransactions() { return this.transactions; }

    public ArrayList<Sale> getPayedSales() {
        ArrayList<Sale> payedSales = new ArrayList<Sale>();

        for (Sale sale : this.transactions) {
            if (sale.isPayed())
                payedSales.add(sale);
        }

        return payedSales;
    }

    protected void setType(ClientType type){
        this.type = type;
    }

    public void addTransaction(Sale transaction) {
        this.transactions.add(transaction);
        this.orderedSales += transaction.getBasePrice();
    }

    public void update(String notificationType, String productId, int price) {
        Notification notification = new Notification(notificationType, productId, price);
        this.notificationMethod.notify(notification);
    }

    public void addNotification(Notification notification) { 
        this.notifications.add(notification);
    }

    public void pay(double price, int daysToDeadline) {
        if (daysToDeadline >= 0)
            addPoints(price);
        else {
            int daysAfterDeadline = daysToDeadline * -1;
            removePoints(daysAfterDeadline);
        }

        addToPayedSales(price);
    }

    public double getDiscount(int deadline, int currentDay, int n) {
        return this.type.getDiscount(deadline, currentDay, n);
    }

    public void setPoints(double amount) { this.points = amount; }
    public void removePoints(int daysAfterDeadline) { this.type.downgrade(daysAfterDeadline); }

    public void addPoints(double basePrice) {
        this.points += basePrice * 10;
        this.type.upgrade();
    }


    public void addToPayedSales(double value) { this.payedSales += value; }

    public String toString(boolean showNotifications) {
        String clientString = this.id + "|" + this.name + "|" + this.address + "|" + this.type + "|" + this.orderedSales + "|" + (int) this.payedSales; 

        if (showNotifications) {
            for (Notification notification : this.notifications)
                clientString += "\n" + notification.toString();

            this.notifications.clear();
        }
        
        return clientString;
    } 
}