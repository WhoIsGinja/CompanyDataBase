package woo;

import woo.exceptions.*;
import java.io.*;
import java.util.*;

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {
  
  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();

  private boolean madeChanges = true;

  private void setMadeChanges(boolean madeChanges) {
    this.madeChanges = madeChanges;
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */

  public String getFileName() {
    return this._filename;
  }

  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
    if (this.madeChanges) {      
      ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this._filename)));
      // Write the store object to the given file
      out.writeObject(this._store);
    
      out.close();
      setMadeChanges(false);
    }
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))); 
    // Gets the store object from the file
    this._filename = filename;
    _store = (Store) in.readObject();

    in.close();
  }

  /** 
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(textfile);
    }
  }



  /* ################# :Products: ################# */

  /**
   * Requests all products stored in _store. They are passed as 
   * a Map which the keys are Strings and the values are Product   
   */

  public void registerProductBook(String productId, String title, String author, String ISBN, String supplierId, int price, int criticalValue) throws DuplicateKeyException, SupplierKeyDoesntExistException {
    if (checkProductKeyExists(productId))
      throw new DuplicateKeyException();
    
    if (!checkSupplierKeyExists(supplierId))
      throw new SupplierKeyDoesntExistException();

      
    
    _store.registerProductBook(productId, title, author, ISBN, supplierId, price, criticalValue, 0);
    setMadeChanges(true);
  }

  public void registerProductBox(String productId, String serviceType, String supplierId, int price, int criticalValue) throws DuplicateKeyException, SupplierKeyDoesntExistException, ServiceTypeDoesntExistException {
    if(checkProductKeyExists(productId))
      throw new DuplicateKeyException();
    
    if(!checkSupplierKeyExists(supplierId))
      throw new SupplierKeyDoesntExistException();
    
    if (!existServiceType(serviceType))
      throw new ServiceTypeDoesntExistException();
    
    
    _store.registerProductBox(productId, serviceType, supplierId, price, criticalValue, 0); 
    setMadeChanges(true);
  }
      
  public void registerProductContainer(String productId, String serviceType, String serviceLevel, String supplierId, int price, int criticalValue) throws DuplicateKeyException, SupplierKeyDoesntExistException, ServiceTypeDoesntExistException, ServiceLevelDoesntExistException {
    if(checkProductKeyExists(productId))
      throw new DuplicateKeyException();
  
    if(!checkSupplierKeyExists(supplierId))
      throw new SupplierKeyDoesntExistException();
    
    if (!existServiceType(serviceType))
      throw new ServiceTypeDoesntExistException();

    if (!existServiceLevel(serviceLevel))
      throw new ServiceLevelDoesntExistException();
    
    _store.registerProductContainer(productId, serviceType, serviceLevel, supplierId, price, criticalValue, 0);
    setMadeChanges(true);
  }

  public Collection<Product> requestAllProducts() {
    return _store.requestAllProducts();
  }

  public Collection<Product> requestProductsUnder(int priceLimit) {
    return _store.requestProductsUnder(priceLimit);
  }

  public int getProductStock(String productId) {
    if ( checkProductKeyExists(productId)) {
      Product product = _store.requestProduct(productId);
      return product.getStock();
    }

    return -1;
  }

  public void changePrice(String productId, int newPrice) throws ProductKeyDoesntExistException {
    if ( !checkProductKeyExists(productId))
      throw new ProductKeyDoesntExistException();

    if(newPrice > 0) {
      _store.changePrice(productId, newPrice);
      setMadeChanges(true);
    }
  }

  private boolean checkProductKeyExists(String productId) {
    return _store.checkProductKeyExists(productId);
  }

  public  boolean existServiceType(String serviceType) {
    return _store.validServiceType(serviceType);
  }

  public boolean existServiceLevel(String serviceLevel) {
    return _store.validServiceLevel(serviceLevel);
  }

  public boolean toggleProductNotifications(String clientId, String productId) throws ClientKeyDoesntExistException, ProductKeyDoesntExistException {
    if ( !checkClientKeyExists(clientId) )
      throw new ClientKeyDoesntExistException();

    if ( !checkProductKeyExists(productId) )
      throw new ProductKeyDoesntExistException();

    setMadeChanges(true);
    return _store.toggleProductNotifications(clientId, productId);
  }

  /* ################# :Supplier: ################# */

  /**
   * Requests all suppliers stored in _store. They are passed as 
   * a Map which the keys are Strings and the values are Supplier   
   */
  public Collection<Supplier> requestAllSuppliers() {
    return _store.requestAllSuppliers();
  }

  public Collection<Order> requestSupplierTransactions(String supplierId) throws SupplierKeyDoesntExistException {
    if ( !checkSupplierKeyExists(supplierId))
      throw new SupplierKeyDoesntExistException();
    
    return _store.requestSupplierTransactions(supplierId);
  }

  private boolean checkSupplierKeyExists(String supplierId) {
    return _store.checkSupplierKeyExists(supplierId);
  }

  public void registerSupplier(String supplierId, String name, String address) throws DuplicateKeyException {
    if (checkSupplierKeyExists(supplierId))
      throw new DuplicateKeyException();
    
    _store.registerSupplier(supplierId, name, address);
    setMadeChanges(true);
  }

  public boolean toggleTransactions(String supplierId) throws SupplierKeyDoesntExistException {
    if ( !checkSupplierKeyExists(supplierId))
      throw new SupplierKeyDoesntExistException();
    
    setMadeChanges(true);
    return _store.toggleTransactions(supplierId);
  }

  /* ################# :Clients: ################# */

  /**
   * @param id Client identifier
   * @param name Client attribute 
   * @param address Client attribute
   * 
   * @throws DuplicateKeyException cant have two clients with the same key
   * 
   * Given id, name, and address of a client register  
   * the Client in our store "database"
   */
  public void registerClient(String id, String name, String address) throws DuplicateKeyException{
    if (checkClientKeyExists(id)) {
      throw new DuplicateKeyException();
    }

    _store.registerClient(id, name, address);
    setMadeChanges(true);
  }

  public Collection<Sale> requestClientTransactions(String clientId) throws ClientKeyDoesntExistException {
    if ( !checkClientKeyExists(clientId))
      throw new ClientKeyDoesntExistException();
    
    return _store.requestClientTransactions(clientId);
  }

  /** 
   * @param clientId Client identifier
   *
   * Given clientId, it checks if a certain client exists in
   * the client "database"
   */
  private boolean checkClientKeyExists(String clientId) {
    return _store.checkClientKeyExists(clientId);
  }

  /** 
   * @param clientId Client identifier
   *
   * @throws UnknownKeyException key needs to exist in the "database"
   *   
   * Given clientId, requests the client and transforms  
   * it into a string to be shown on app 
   */
  public String clientToString(String clientId, boolean showNotifications) throws UnknownKeyException {
    if (! checkClientKeyExists(clientId)) {
      throw new UnknownKeyException();
    }

    return _store.requestClient(clientId).toString(showNotifications);
  }

  /**
   * Requests all clients stored in _store. They are passed as 
   * a Map which the keys are Strings and the values are Client   
   */
  public Collection<Client> requestAllClients() {
    return _store.requestAllClients();
  }

  public Collection<Sale> requestClientPayments(String clientId) throws UnknownKeyException {
    if ( !checkClientKeyExists(clientId)) {
      throw new UnknownKeyException();
    }

    return _store.requestClientPayments(clientId);
  }

  /* ################# :Transactions: ################# */

  public void registerSale(String clientId, int deadLine, String productId, int amount) throws ClientKeyDoesntExistException, ProductKeyDoesntExistException, MoreAmountThanStockException {
    if ( !checkClientKeyExists(clientId))
      throw new ClientKeyDoesntExistException();
    
    if ( !checkProductKeyExists(productId))
      throw new ProductKeyDoesntExistException();

    if ( !_store.lessAmountThanStock(productId, amount))
      throw new MoreAmountThanStockException();

    _store.registerSale(clientId, deadLine, productId, amount);
    setMadeChanges(true);
  } 

  public void payTransaction(int transactionId) throws UnknownKeyException {
    if (!checkTransactionKeyExists(transactionId)) 
      throw new UnknownKeyException();

    _store.payTransaction(transactionId);
    setMadeChanges(true);
  }

  public void registerOrder(String supplierId, TreeMap<String, Integer> productsAndAmounts) {
    int orderId = _store.registerEmptyOrder(supplierId);
    productsAndAmounts.forEach((productId, amount) -> _store.addElementToOrder(orderId, productId, amount));
    
    _store.payOrder(orderId);
    setMadeChanges(true);
  }

  public void checkIfIsValidSupplier(String supplierId) throws SupplierKeyDoesntExistException, SupplierCantMakeTransactionsException {
    if ( !checkSupplierKeyExists(supplierId))
      throw new SupplierKeyDoesntExistException();

    if ( !_store.authorizedSupplier(supplierId))
      throw new SupplierCantMakeTransactionsException();
  }

  public void checkIfIsValidProduct(String productId, String supplierId) throws ProductKeyDoesntExistException, SupplierDoesntSupplyProductException {
    if ( !checkProductKeyExists(productId)) {
      throw new ProductKeyDoesntExistException();
    }
    
    if ( !_store.supplierSuppliesProduct(supplierId, productId)) {
      throw new SupplierDoesntSupplyProductException();
    }
  }

  public boolean checkTransactionKeyExists(int transactionId) {
    return _store.checkTransactionKeyExists(transactionId);
  }

  public String transactionToString(int transactionId) throws UnknownKeyException {
    if ( !checkTransactionKeyExists(transactionId))
      throw new UnknownKeyException();

    return _store.transactionToString(transactionId);
  }
  
  
  /* ################# :Date: ################# */

  /**
   * @param numDays number of days to advance
   *
   * Given numDays, it advances the current date in the program
   */
  public void advanceDate(int numDays) throws NegativeDateException {
    if (numDays < 0)
      throw new NegativeDateException();
    
    _store.advanceDate(numDays);
    setMadeChanges(true);
  }

  /**
    * Returns the current date 
    */
  public int requestDate() {
    return _store.getDate();
  }

  public int requestAvailableBalance() {
    return _store.requestAvailableBalance();
  }

  public int requestAccountingBalance() {
    return _store.requestAccountingBalance();
  }





}
