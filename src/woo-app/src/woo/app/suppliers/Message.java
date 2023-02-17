package woo.app.suppliers;

/** Supplier menu interaction messages. */
public class Message {

  /** @return string prompting for a unique key. */
  public static final String requestSupplierKey() {
    return "Identificador do fornecedor: ";
  }

  /** @return string prompting for a name. */
  public static final String requestSupplierName() {
    return "Nome do fornecedor: ";
  }

  /**
   * @return string prompting for an address.
   */
  public static final String requestSupplierAddress() {
    return "Endereço do fornecedor: ";
  }

  /** @return "SIM" */
  public static final String yes() {
    return "SIM";
  }

  /** @return "NÃO" */
  public static final String no() {
    return "NÃO";
  }

  /** @return description of operation result. */
  public static final String transactionsOn(String supplierKey) {
    return "Fornecedor '" + supplierKey + "': transacções ACTIVAS";
  }

  /** @return description of operation result. */
  public static final String transactionsOff(String supplierKey) {
    return "Fornecedor '" + supplierKey + "': transacções INACTIVAS";
  }


}