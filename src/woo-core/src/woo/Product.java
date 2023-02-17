package woo;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Subject, Serializable {
    private static final long serialVersionUID = 201708301010L;
    
    private String id;    
    private int price;
    private int criticalValue;
    private int stock = 0;
    private String supplierID;

    private int period;
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public Product(String id, int price, int criticalValue, String supplierID) {
        this.id = id;
        this.price = price;
        this.criticalValue = criticalValue;
        this.supplierID = supplierID;
    }

    /* Getters */
    public String getId() { return this.id; } 
    public int getPrice() { return this.price; } 
    public int getCriticriticalValue() { return this.criticalValue; } 
    public int getStock() { return this.stock; } 
    public String getSupplierId() { return this.supplierID; } 
    public int getPeriod() { return this.period; }

    public void setPeriod(int period) { this.period = period; }

    public void setPrice(int newPrice) { 
        int currentPrice = this.price;
        this.price = newPrice;

        if (currentPrice > newPrice)
            this.notifyBargain();
    }
    
    public void addStock(int numCopies) {
        int initialStock = this.stock;
        this.stock += numCopies;

        if (initialStock == 0 && this.stock > 0)
            this.notifyNew();
    }

    public void removeStock(int amount) {
        this.stock -= amount;
    }

    public boolean priceLessThan(int price) {
        return this.price < price;
    }


    @Override
    public void attachObserver(Observer observer) {
        this.observers.add(observer);
    }
    
    @Override
    public boolean dettachObserver(Observer observer) {
        return this.observers.remove(observer);
    }

    @Override
    public void notifyNew() {
        for (Observer observer : this.observers) {
            observer.update("NEW", this.id, this.price);
        }
    }

    @Override
    public void notifyBargain() {
        for (Observer observer : this.observers) {
            observer.update("BARGAIN", this.id, this.price);
        } 
    }


    @Override
    public String toString() {
        return this.id + "|" + this.supplierID + "|" + this.price + "|" + this.criticalValue + "|" + this.stock;
    }

 }