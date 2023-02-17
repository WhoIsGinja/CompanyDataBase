package woo;
import java.io.Serializable;

public abstract class Transaction implements Serializable {
    private static final long serialVersionUID = 201708301010L;
    
    private int id;
    
    private int basePrice = 0;
    private double payedPrice = 0;
    private boolean payed = false;

    private int payday = 0;

    public Transaction(int id) {
        this.id = id;
    }

    public int getBasePrice() { return this.basePrice; }
    public int getPayday() { return this.payday; }
    public double getPayedPrice() { return this.payedPrice; }
    public boolean isPayed() { return this.payed; }

    public void setPayed() { this.payed = true; }
    public void setPayday(int day) { this.payday = day; }
    public void setPayedPrice(double price) { this.payedPrice = price; }
    public void addToBasePrice(int value) { this.basePrice += value; }
    
    public abstract void updatePayedPrice(int currentDay);


    public abstract double pay(int day);
    public abstract void addProduct(Product product, int amount);

    @Override
    public String toString() {
        return "" + this.id;
    }
}