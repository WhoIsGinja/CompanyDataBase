package woo;


class Container extends Box {
    private static final long serialVersionUID = 201708301010L;

    private String serviceLevel;

    enum ServiceLevels {
        B4, C4, C5, DL
    }

    public Container(String id, String serviceType, String serviceLevel, String supplierID, int price, int criticalValue) {
        super(id,serviceType, supplierID, price, criticalValue);
        this.serviceLevel = serviceLevel;

        setPeriod(8);
    }

    /* Getters */
    public String getServiceLevel() { return this.serviceLevel; }

    @Override
    public String toString() {
        return  super.toString().replaceFirst("BOX","CONTAINER") + "|" + this.serviceLevel;
    }
}