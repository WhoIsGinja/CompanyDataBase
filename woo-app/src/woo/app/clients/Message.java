package woo.app.clients;

/** Messages for client menu interactions. */
public class Message {
  
  /** @return string prompting for a client identifier. */
  public static final String requestClientKey() {
    return "Identificador do cliente: ";
  }

  /** @return string prompting for a client name. */
  public static final String requestClientName() {
    return "Nome do cliente: ";
  }

  /** @return string prompting for an address. */
  public static final String requestClientAddress() {
    return "Endereço do cliente: ";
  }

  /** @return string prompting for a product identifier. */
  public static final String requestProductKey() {
    return "Identificador do produto: ";
  }

  /** @return description of operation result. */
  public static final String notificationsOn(String clientKey, String productKey) {
    return "Cliente '" + clientKey + "': notificações do produto '" + productKey + "' ACTIVAS";
  }

  /** @return description of operation result. */
  public static final String notificationsOff(String clientKey, String productKey) {
    return "Cliente '" + clientKey + "': notificações do produto '" + productKey + "' INACTIVAS";
  }

}