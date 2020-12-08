package woo.app.exceptions;

/** Messages for error reporting. */
public class Message {

  /**
   * @return string with "file not found" message.
   */
  public static final String fileNotFound() {
    return "O ficheiro não existe.";
  }

  /**
   * @param filename
   * @return string with "file not found" message (more elaborate).
   */
  public static final String fileNotFound(String filename) {
    return "O ficheiro '" + filename + "' não existe.";
  }

  /**
   * @param filename
   * @return string with problem description.
   */
  public static final String problemOpeningFile(String filename) {
    return "Problema ao abrir '" + filename + "'.";
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static final String unknownClientKey(String key) {
    return "O cliente '" + key + "' não existe.";
  }

  /**
   * @param key client key
   * @return string reporting a duplicate client
   */
  public static final String duplicateClientKey(String key) {
    return "O cliente '" + key + "' já existe.";
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static final String unknownProductKey(String key) {
    return "O produto '" + key + "' não existe.";
  }

  /**
   * @param key client key
   * @return string reporting a duplicate product
   */
  public static String duplicateProductKey(String key) {
    return "O produto '" + key + "' já existe.";
  }

  /**
   * @param key supplier key
   * @return string reporting a duplicate supplier
   */
  public static final String duplicateSupplierKey(String key) {
    return "Fornecedor '" + key + "' já existe.";
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static final String unknownSupplierKey(String key) {
    return "O fornecedor '" + key + "' não existe.";
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static final String unauthorizedSupplier(String key) {
    return "O fornecedor '" + key + "' não tem as transacções activas.";
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static final String wrongSupplier(String skey, String pkey) {
    return "O fornecedor '" + skey + "' não fornece o produto '" + pkey + "'.";
  }

  /**
   * @param product key
   * @param requested
   * @param available 
   * @return string with requested quantity.
   */
  public static final String unavailable(String key, int requested, int available) {
    return "Produto '"+ key +"': pedido=" + requested + ", existências=" + available;
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static final String unknownTransactionKey(int key) {
    return "A transacção '" + key + "' não existe.";
  }

  /**
   * @param key
   * @return string with problem description.
   */
  public static String duplicateTransactionKey(int key) {
    return "A transacção '" + key + "' já existe.";
  }

  /**
   * @param type Type of service.
   * @return string with problem description.
   */
  public static final String unknownServiceType(String type) {
    return "Tipo de serviço desconhecido: '" + type + "'.";
  }

  /**
   * @param level service level.
   * @return string with problem description.
   */
  public static final String unknownServiceLevel(String level) {
    return "Nível de serviço desconhecido: '" + level + "'.";
  }

  /**
   * @param date Bad date..
   * @return string with problem description.
   */
  public static String invalidDate(int date) {
    return "Data inválida: " + date;
  }

}