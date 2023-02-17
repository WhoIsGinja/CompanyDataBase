package woo.app.products;

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

  /**
   * @return string prompting for supplier identifier
   */
  public static final String requestSupplierKey() {
    return "Identificador do fornecedor: ";
  }

  /**
   * @return string prompting for service type.
   */
  public static String requestServiceType() {
    return "Tipo de serviço: ";
  }

  /**
   * @return string prompting for service level.
   */
  public static String requestServiceLevel() {
    return "Nível de serviço: ";
  }

  /**
   * @return string prompting for price.
   */
  public static String requestPrice() {
    return "Preço: ";
  }

  /**
   * @return string prompting for critical value.
   */
  public static String requestStockCriticalValue() {
    return "Valor crítico para as existências: ";
  }

  /**
   * @return string prompting for book title.
   */
  public static String requestBookTitle() {
    return "Titulo do livro: ";
  }

  /**
   * @return string prompting for book author.
   */
  public static String requestBookAuthor() {
    return "Autor do livro: ";
  }

  /**
   * @return string prompting for ISBN.
   */
  public static String requestISBN() {
    return "ISBN: ";
  }

}
