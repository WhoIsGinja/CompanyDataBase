package woo;

public class EliteType extends ClientType {
    private static final long serialVersionUID = 201708301010L;

    private final int allowedDaysAfterDeadLine = 15;
    private final double percentageToRemove = 0.75; 
    
    public EliteType(Client client) {
        super(client);
    }

    @Override
    public void upgrade() { }

    @Override
    public void downgrade(int daysAfterDeadLine) {
        if (daysAfterDeadLine > allowedDaysAfterDeadLine) {
            
            double currentPoints = getClient().getPoints();
            double pointsAfterDiscount = currentPoints - (currentPoints * percentageToRemove); 
            getClient().setPoints(pointsAfterDiscount);

            if (pointsAfterDiscount <= getEliteMinimum()) {
                getClient().setType(new SelectionType(getClient()));
            }
        }
    }

    @Override
    public String toString() {
        return "ELITE";
    }

    public double getDiscountPeriod2(int days) { return 1 - 0.1; }
    public double getDiscountPeriod3(int days) { return 1 - 0.05; }
    public double getDiscountPeriod4(int days) { return 1; }
}