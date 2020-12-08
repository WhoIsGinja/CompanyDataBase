package woo;

public class NormalType extends ClientType {
    private static final long serialVersionUID = 201708301010L;
   
    public NormalType(Client client) {
        super(client);
    }

    @Override
    public void upgrade() {
        double points = getClient().getPoints();

        if (points > getEliteMinimum()) {
            getClient().setType(new EliteType(getClient()));
        } else if (points > getSelectionMinimum()) {
            getClient().setType(new SelectionType(getClient()));
        }
    }

    @Override
    public void downgrade(int daysAfterDeadLine) { }


    @Override
    public String toString() {
        return "NORMAL";
    }


    public double getDiscountPeriod2(int days) { return 1; }
    public double getDiscountPeriod3(int days) { return 1 + (0.05 * days); }
    public double getDiscountPeriod4(int days) { return 1 + (0.1 * days); }

}