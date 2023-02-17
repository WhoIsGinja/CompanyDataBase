package woo;

public class Sale extends Transaction {
    private static final long serialVersionUID = 201708301010L;

    private Client client;

    private Product product;
    private int productAmount;
    
    private int deadline;


    public Sale(int id, Client client, Product product, int productAmount, int deadline) {
        super(id);
        this.client = client;

        addProduct(product, productAmount);
        
        this.deadline = deadline;

        addToBasePrice(calculateBasePrice());
        this.product.removeStock(productAmount);
    }

    public int calculateBasePrice() {
        return this.product.getPrice() * this.productAmount;
    }

    @Override
    public void updatePayedPrice(int currentDay) {
        if ( !this.isPayed() ) {
            double discount = client.getDiscount(this.deadline, currentDay, this.product.getPeriod());
            double price = getBasePrice() * discount;


            setPayedPrice(price);
        }
    }

    @Override
    public double pay(int currentDay) {
        if ( !this.isPayed() ) {

            int daysToDeadline = this.deadline - currentDay;
            
            updatePayedPrice(currentDay);
            double price = getPayedPrice();
            
            this.client.pay(price, daysToDeadline);

            setPayday(currentDay);
            setPayed();

            return price;
        }

        return 0;
    }

    @Override
    public void addProduct(Product product, int amount) {
        this.product = product;
        this.productAmount = amount;
    } 

    @Override
    public String toString() {
        String saleToString = super.toString() + "|" + this.client.getId() + "|" + this.product.getId() + "|" + this.productAmount + "|" + getBasePrice() + "|" + (int) Math.round(getPayedPrice()) + "|" + this.deadline;

        if (isPayed())
            saleToString += "|" + getPayday();

        return saleToString;
    }
}