package woo;

import java.io.Serializable;

public class Notification implements Serializable {
    private static final long serialVersionUID = 201708301010L;
    
    private String notificationType;
    private String productId;
    private int productPrice;

    public Notification(String notificationType, String productId, int productPrice) {
        this.notificationType = notificationType;
        this.productId = productId;
        this.productPrice = productPrice;        
    }
    
    @Override
    public String toString() {
        return this.notificationType + "|" + this.productId + "|" + this.productPrice;
    }
}