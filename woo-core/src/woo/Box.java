package woo;

class Box extends Product {
    private static final long serialVersionUID = 201708301010L;

    private String serviceType;

    enum ServiceTypes {
        NORMAL, AIR, EXPRESS, PERSONAL
    }

    public Box(String id, String serviceType, String supplierID, int price, int criticalValue) {
        super(id, price, criticalValue, supplierID);
        this.serviceType = serviceType;

        setPeriod(5);
    }

    /* Getters */
    public String getServiceType() { return this.serviceType; }

    @Override
    public String toString() {
        return "BOX" + "|" + super.toString() + "|" + this.serviceType;
    }
}
