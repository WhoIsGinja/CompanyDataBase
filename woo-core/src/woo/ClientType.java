package woo;
import java.io.Serializable;

public abstract class ClientType implements Serializable {
    private static final long serialVersionUID = 201708301010L;

    protected Client client;

    private final int selectionMinimum = 2000;
    private final int eliteMinimum = 25000;
    
    protected ClientType(Client client) {
        this.client = client;
    }

    protected Client getClient() { return this.client; } 
    protected int getSelectionMinimum() { return this.selectionMinimum; }
    protected int getEliteMinimum() { return this.eliteMinimum; }

    public abstract void upgrade();
    public abstract void downgrade(int daysAfterDeadLine);

    public double getDiscount(int deadLine, int currentDay, int n) {
        int daysToDeadline = deadLine - currentDay;
        int daysAfterDeadLine = currentDay - deadLine;

        if (daysToDeadline >= n) {
            return getDiscountPeriod1();
        } else if (0 <= daysToDeadline && daysToDeadline < n) {
            return getDiscountPeriod2(daysToDeadline);
        } else if (0 < daysAfterDeadLine && daysAfterDeadLine <= n ) {
            return getDiscountPeriod3(daysAfterDeadLine);
        } else {
            return getDiscountPeriod4(daysAfterDeadLine);
        }
    }

    public double getDiscountPeriod1() { return (1 - 0.1); }
    public abstract double getDiscountPeriod2(int days);
    public abstract double getDiscountPeriod3(int days);
    public abstract double getDiscountPeriod4(int days);

}