package woo;

import java.io.Serializable;
import java.util.ArrayList;

public class Supplier implements Serializable{
    private static final long serialVersionUID = 201708301010L;
    
    private String id;
    private String name;
    private String address;
    private boolean canMakeTransactions = true;
    private ArrayList<Order> transactions;


    public Supplier(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;

        this.transactions = new ArrayList<Order>();
    }

    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getAddress() { return this.address; }
    public boolean getCanMakeTransactions() { return this.canMakeTransactions; }
    public ArrayList<Order> getTransactions() { return this.transactions; }
    
    public void addTransaction(Order transaction) {
        this.transactions.add(transaction);
    }

    public boolean toggleTransactions() {
        this.canMakeTransactions = ! this.canMakeTransactions;
        return this.canMakeTransactions;
    }

    public String toString(String yes, String no) {
        return this.id + "|" + this.name + "|" + this.address + "|" + ((this.canMakeTransactions) ? yes : no);
    }

}
