package woo;

public interface Observer {
    public void update(String notificationType, String productId, int price);
}