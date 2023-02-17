package woo;

public class SelectionType extends ClientType {
    private static final long serialVersionUID = 201708301010L;
    
    private final int allowedDaysAfterDeadLine = 2;
    private final double percentageToRemove = 0.9; 

    public SelectionType(Client client) {
        super(client);
    }

    @Override
    public void upgrade() { 
        double points = getClient().getPoints();
 
        if (points > getEliteMinimum()) {
            getClient().setType(new EliteType(getClient()));
        }
    }

    @Override
    public void downgrade(int daysAfterDeadLine) {
        if (daysAfterDeadLine > allowedDaysAfterDeadLine) {
            
            double currentPoints = getClient().getPoints();
            double pointsAfterDiscount = currentPoints - (currentPoints * percentageToRemove);
            getClient().setPoints(pointsAfterDiscount);
            
            if (pointsAfterDiscount <= getSelectionMinimum()) {
                getClient().setType(new NormalType(getClient()));
            }
        }
    }

    @Override
    public String toString() {
        return "SELECTION";
    }

    public double getDiscountPeriod2(int days) { 
        if (days >= 2)
            return 1 - 0.05;
        else 
            return 1;
    }
    public double getDiscountPeriod3(int days) {
        if (days > 1) 
            return 1 + (0.02 * days);
        else 
            return 1;
    }
    public double getDiscountPeriod4(int days) { return 1 + (0.05 * days); }
}