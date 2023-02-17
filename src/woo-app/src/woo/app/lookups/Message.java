package woo.app.lookups;

/**
 * Messages.
 */
public class Message {

  /**
   * @return string prompting for product identifier
   */
  public static final String requestProductKey() {
    return "Identificador do produto: ";
  }

  /** @return string prompting for a client identifier. */
  public static final String requestClientKey() {
    return "Identificador do cliente: ";
  }

  /**
   * @return string prompting for supplier identifier
   */
  public static final String requestSupplierKey() {
    return "Identificador do fornecedor: ";
  }
  
  /**
   * @return string prompting for identifier
   */
  public static final String requestTransactionKey() {
    return "Identificador da transacção: ";
  }

  /**
   * @return string prompting for a price.
   */
  public static final String requestPriceLimit() {
    return "Preço: ";
  }

  /**
   * @return string prompting for a delay.
   */
  public static final String requestDelay() {
    return "Atraso: ";
  }

}