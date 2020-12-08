package woo;

import java.io.Serializable;

public class DefaultMethod implements NotificationMethod, Serializable {
    private static final long serialVersionUID = 201708301010L;
    
    private Client client;

    public DefaultMethod(Client client) {
        this.client = client;
    }

    public void notify(Notification notification) {
        client.addNotification(notification);
    }
}