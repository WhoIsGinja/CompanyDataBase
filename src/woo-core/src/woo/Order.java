package woo;
import java.util.ArrayList;

public class Order extends Transaction {
    private static final long serialVersionUID = 201708301010L;
    
    private Supplier supplier;
    
    private int numberOfProducts = 0;
    private ArrayList<Product> products;
    private ArrayList<Integer> amounts;

    public Order(int id, Supplier supplier){
        super(id);
        this.supplier = supplier;

        this.products = new ArrayList<Product>();
        this.amounts = new ArrayList<Integer>();
    }

    public int getProductValue(int productPrice, int amount) {
        return productPrice * amount;
    }

    public void updatePayedPrice(int currentDay) {
        if ( !this.isPayed() ) {
            int price = getBasePrice() * -1;
            this.setPayedPrice(price);
        }
    }

    @Override
    public double pay(int day) {
        if ( !this.isPayed() ) {
            setPayday(day);

            updatePayedPrice(day);
            setPayed();
        }

        return 0;
    }

    @Override
    public void addProduct(Product product, int amount) {
        products.add(product);
        amounts.add(amount);

        addToBasePrice(getProductValue(product.getPrice(), amount));
        product.addStock(amount);
        
        numberOfProducts++;
    }

    @Override
    public String toString() {
        String orderToString = super.toString() + "|" + this.supplier.getId() + "|" + getBasePrice() + "|" + getPayday();
        
        for (int i = 0; i < numberOfProducts; i++) {
            orderToString += ("\n" + this.products.get(i).getId() + "|" + this.amounts.get(i)); 
        }

        return orderToString;
    }
}