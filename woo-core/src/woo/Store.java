package woo;

import woo.exceptions.*;

import java.io.*;

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  private int currentDate = 0;
  private int currentTransactionId = 0;

  private double availableBalance = 0;
  private double accountingBalance = 0;
  
  private TreeMap<String, Product> products = new TreeMap<String, Product>(String.CASE_INSENSITIVE_ORDER);
  private TreeMap<String, Client> clients = new TreeMap<String, Client>(String.CASE_INSENSITIVE_ORDER);
  private TreeMap<String, Supplier> suppliers = new TreeMap<String, Supplier>(String.CASE_INSENSITIVE_ORDER);
  private TreeMap<Integer, Transaction> transactions = new TreeMap<Integer , Transaction>();


  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  /**
   * Receives a file and parses its lines into parameters
   *
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException {
    BufferedReader reader = new BufferedReader(new FileReader(txtfile));
    String command;

    while ((command = reader.readLine()) != null) {
      String[] fields = command.split("\\|");
      registerFromFields(fields);
    }

    reader.close();  
  }


  /**
  * With the parsed lines from the function importFile
  * creates the associated object   
  *
  * @param fields fields received from a line of input.
  */
  public void registerFromFields(String[] fields){
    String type = fields[0];
    int price; int criticalValue; int copies;

    switch (type) {
      case "SUPPLIER":
        registerSupplier(fields[1], fields[2], fields[3]);
        break;
      case "CLIENT":
        registerClient(fields[1], fields[2], fields[3]);
        break;
      case "BOX":
        price = Integer.parseInt(fields[4]);
        criticalValue = Integer.parseInt(fields[5]);
        copies = Integer.parseInt(fields[6]);
        registerProductBox(fields[1], fields[2], fields[3], price, criticalValue, copies);
        break;
      case "CONTAINER":
        price = Integer.parseInt(fields[5]);
        criticalValue = Integer.parseInt(fields[6]);
        copies = Integer.parseInt(fields[7]);
        registerProductContainer(fields[1], fields[2], fields[3], fields[4], price, criticalValue, copies);
        break;
      case "BOOK":
        price =Integer.parseInt(fields[6]);
        criticalValue = Integer.parseInt(fields[7]);
        copies = Integer.parseInt(fields[8]);
        registerProductBook(fields[1], fields[2], fields[3], fields[4], fields[5], price, criticalValue, copies);
        break;
      
      default:

        break;
    }
  }

  /* ################# :Products: ################# */
  
  /**
  * Creates and stores a product container, with the given arguments.
  *
  * @param id product identifier, serves also as a key in Map
  * @param serviceType  
  * @param serviceLevel
  * @param supplierID product supplier identifier
  * @param price 
  * @param criticalValue 
  * @param copies number of copies given when creating the product (initial stock).
  */
  public void registerProductContainer(String id, String serviceType, String serviceLevel, String supplierID, int price, int criticalValue, int copies) {
    Supplier supplier = requestSupplier(supplierID);

    Box newContainer = new Container(id, serviceType, serviceLevel, supplier.getId(), price, criticalValue);
    newContainer.addStock(copies);

    addNotificationClientFor(newContainer);

    this.products.put(id, newContainer);
  }

  /**
  * Creates and stores a product box, with the given arguments.
  *
  * @param id product identifier, serves also as a key in Map
  * @param serviceType  
  * @param supplierID product supplier identifier
  * @param price 
  * @param criticalValue 
  * @param copies number of copies given when creating the product (initial stock).
  */
  public void registerProductBox(String id, String serviceType, String supplierID, int price, int criticalValue, int copies) {
    Supplier supplier = requestSupplier(supplierID);
    
    Box newBox = new Box(id, serviceType, supplier.getId(), price, criticalValue);
    newBox.addStock(copies);

    addNotificationClientFor(newBox);

    this.products.put(id, newBox);
  }

  /** 
  * Creates and stores a product box, with the given arguments.
  *
  * @param id product identifier, serves also as a key in Map
  * @param title  Book attribute
  * @param author Book attribute   
  * @param ISBN   Book attribute
  * @param supplierID product supplier identifier
  * @param price 
  * @param criticalValue 
  * @param copies number of copies given when creating the product (initial stock).
  */
  public void registerProductBook(String id, String title, String author, String ISBN, String supplierID, int price, int criticalValue, int copies) {
    Supplier supplier = requestSupplier(supplierID);
    
    Book newBook = new Book(id, title, author, ISBN, supplier.getId(), price, criticalValue);
    newBook.addStock(copies);

    addNotificationClientFor(newBook);

    this.products.put(id, newBook);
  }

  public void addNotificationClientFor(Product product){
    for(Client client : this.clients.values())
      product.attachObserver(client);
  }

  /** 
  * Places all products in a Map to preserve lexicographic order, and returns it
  */
  public Collection<Product> requestAllProducts() {
    return products.values();
  }

  public Collection<Product> requestProductsUnder(int priceLimit) {
    ArrayList<Product> productsLimit = new ArrayList<Product>();
    
    for(Product product : products.values()) {
      if(product.priceLessThan(priceLimit))
        productsLimit.add(product);
    }

    return productsLimit;
  }

  public Product requestProduct(String productId) {
    Product product = products.get(productId);

    return product;
  }

  public void changePrice(String productId, int newPrice) {
    Product product = requestProduct(productId);
    product.setPrice(newPrice);
  }

  public boolean checkProductKeyExists(String productId) {
    return products.containsKey(productId);
  }

  public boolean validServiceType(String serviceType) {
    for (Container.ServiceTypes type : Box.ServiceTypes.values())
        if(serviceType.equals(type.toString()))
            return true;
    
    return false;
  }

  public boolean validServiceLevel(String serviceLevel) {
    for (Container.ServiceLevels level : Container.ServiceLevels.values())
      if (serviceLevel.equals(level.toString()))
        return true;

    return false;
  } 

  public boolean toggleProductNotifications(String clientId, String productId) {
    Client client = requestClient(clientId);
    Product product = requestProduct(productId);

    boolean hasRemoved = product.dettachObserver(client);
    
    if (!hasRemoved)
      product.attachObserver(client);
    
    /* 
       True  = Notifications on
       False = Notifications off
    */
    return !hasRemoved;
  }


  /* ################# :Clients: ################# */

  /**
  * Creates and stores a product client, with the given arguments.
  *
  * @param id product identifier, serves also as key in Map
  * @param name Client attribute
  * @param address Client attribute
  */
  public void registerClient(String id, String name, String address) {
    Client newClient = new Client(id, name, address);
    
    activateNotificationsForAllProducts(newClient);
    this.clients.put(id, newClient);
  }

  public void activateNotificationsForAllProducts(Client client) {
    for (Product product : this.products.values())
      product.attachObserver(client);
  }

  public Collection<Sale> requestClientTransactions(String clientId) {
    Client client = requestClient(clientId);
    Collection<Sale> transactionsCollection = client.getTransactions();
    
    for (Sale sale : transactionsCollection) {
      sale.updatePayedPrice(getDate());
    }

    return transactionsCollection;
  }

  /**
  * Checks if the client with the key "clientKey" already exists 
  *
  * @param clientId Client identifier
  */
  public boolean checkClientKeyExists(String clientId) {
    return clients.containsKey(clientId);
  }

/** 
  * @param clientId Client identifier
  *
  * Creates and stores a client, with the given arguments. 
  */
  public Client requestClient(String clientId) {
    Client client = clients.get(clientId);

    return client;
  } 


  /** 
  * Places all clients in a TreeMap to preserve lexicographic order, and returns it
  */
  public Collection<Client> requestAllClients() {

    return clients.values();
  }

  public Collection<Sale> requestClientPayments(String clientId) {
    Client client = clients.get(clientId);
    return client.getPayedSales();
  }


  /* ################# :Suppliers: ################# */
 
  /** 
  * Creates and stores a supplier, with the given arguments. 
  *
  * @param id Client identifier
  * @param name 
  * @param address
  */
  public void registerSupplier(String id, String name, String address) {
    Supplier newSupplier = new Supplier(id, name, address);

    suppliers.put(id, newSupplier);
  }
 
/**
  * Places all suppliers in a TreeMap to preserve lexicographic order, and returns it
  */
  public Collection<Supplier> requestAllSuppliers() {  
    return suppliers.values();
  }

  private Supplier requestSupplier (String supplierId) {
    Supplier supplier = suppliers.get(supplierId);

    return supplier;
  }

  public boolean checkSupplierKeyExists(String supplierId) {
    return suppliers.containsKey(supplierId);
  }

  public boolean authorizedSupplier(String supplierId) {
    Supplier supplier = suppliers.get(supplierId);
    
    return supplier.getCanMakeTransactions();
  }

  public boolean supplierSuppliesProduct(String supplierId, String productId) {
    Product product = requestProduct(productId);

    String orderSupplierId = supplierId;
    String productSupplierId = product.getSupplierId();

    return orderSupplierId.equalsIgnoreCase(productSupplierId);
  }

  public boolean toggleTransactions(String supplierId) {
    Supplier supplier = requestSupplier(supplierId);

    return supplier.toggleTransactions();
  }

  public Collection<Order> requestSupplierTransactions(String supplierId) {
    Supplier supplier = requestSupplier(supplierId);
    Collection<Order> transactionsCollection = supplier.getTransactions();
    
    return transactionsCollection;
  }

  /* ################# :Transactions: ################# */

  public int registerSale(String clientId, int deadLine, String productId, int amount) {
    Client client = requestClient(clientId);
    Product product = requestProduct(productId);

    Sale sale = new Sale(currentTransactionId, client, product, amount, deadLine);
    transactions.put(currentTransactionId, sale);

    /* Add sale to internal state of the client */
    client.addTransaction(sale);

    
    int salePrice = sale.getBasePrice();

    int newTransactionId = currentTransactionId; 
    currentTransactionId++;

    return newTransactionId;
  }

  public void payTransaction(int transactionId) {
    Transaction transaction = requestTransaction(transactionId);

    if (transaction.isPayed())
      return;

    double payedAmount = transaction.pay(getDate());
    this.availableBalance += payedAmount;
  }

  public void payOrder(int orderId) {
    Transaction transaction = requestTransaction(orderId);
    transaction.pay(getDate());

    this.availableBalance -= transaction.getBasePrice();
  }

  public int registerEmptyOrder(String supplierId) {
    Supplier supplier = requestSupplier(supplierId);

    Order order = new Order (currentTransactionId, supplier);
    transactions.put(currentTransactionId, order);

    /* Add order to internal state of the supplier */
    supplier.addTransaction(order);    

    int orderId = currentTransactionId;
    currentTransactionId++;

    return orderId;
  }

  public void addElementToOrder (int orderId, String productId, int amount) {
    Product product = requestProduct(productId);
    Transaction order = requestTransaction(orderId);


    order.addProduct(product, amount);

    /* Update company balance */
    int productValue = product.getPrice() * amount;
  }

  public boolean lessAmountThanStock(String productId, int amount) {
    return products.get(productId).getStock() >= amount;
  }

  public Transaction requestTransaction(int transactionId) {
    Transaction transaction = transactions.get(transactionId);

    return transaction;
  }

  public boolean isSale(int saleId) {
    Transaction transaction = requestTransaction(saleId);
    return transaction instanceof Sale;
  }

  public String transactionToString(int transactionId) {
    Transaction transaction = requestTransaction(transactionId);

    if (transaction instanceof Sale) {
      Sale sale = (Sale) transaction;
      sale.updatePayedPrice(getDate());
    }

    return transaction.toString();
  }

  public boolean checkTransactionKeyExists (int transactionId) {
    return transactions.containsKey(transactionId);
  }

  
  /* ################# :Date: ################# */
  /**
   * @param numDays
   * 
   * Advances the date by the given number of days
   */
  public void advanceDate(int numDays)  {
    this.currentDate += numDays;
  }


  /**
   * Returns the current date
   */
  public int getDate() { return this.currentDate; }


  /* ################# :Balance: ################# */
  public int requestAvailableBalance() {
    return (int) Math.round(this.availableBalance);
  }

  public int requestAccountingBalance() {

    /* Update accounting balance */
    this.accountingBalance = 0;    
    
    for(Transaction transaction : transactions.values()) {
      transaction.updatePayedPrice(getDate());
      this.accountingBalance += transaction.getPayedPrice();
    }

    return (int) Math.round(this.accountingBalance);
  }

}