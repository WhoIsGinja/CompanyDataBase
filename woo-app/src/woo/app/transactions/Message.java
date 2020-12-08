package woo.app.transactions;

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
   * @return string prompting for a date.
   */
  public static final String requestPaymentDeadline() {
    return "Data limite de pagamento: ";
  }

  /**
   * @return string prompting for a quantity.
   */
  public static final String requestAmount() {
    return "Quantidade: ";
  }

  /**
   * @return string with boolean query.
   */
  public static final String requestMore() {
    return "Encomendar mais produtos? (s/n) ";
  }

  /**
   * @return "NÃO"
   */
  public static final String no() {
    return "NÃO";
  }

  /**
   * @return "SIM"
   */
  public static final String yes() {
    return "SIM";
  }

}