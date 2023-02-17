package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import pt.tecnico.po.ui.Form;

import java.util.TreeMap;

import woo.app.exceptions.UnknownSupplierKeyException;
import woo.app.exceptions.UnauthorizedSupplierException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.WrongSupplierException;

import woo.exceptions.SupplierKeyDoesntExistException;
import woo.exceptions.SupplierCantMakeTransactionsException;
import woo.exceptions.ProductKeyDoesntExistException;
import woo.exceptions.SupplierDoesntSupplyProductException;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {

  Input<String> supplierId;

  Input<String> productId;
  Input<Integer> amount;

  Input<Boolean> addMore;
  int orderId;

  Form askForProducts;

  public DoRegisterOrderTransaction(Storefront receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);

    supplierId = _form.addStringInput(Message.requestSupplierKey());

    askForProducts = new Form(Label.REGISTER_ORDER_TRANSACTION);
    productId = askForProducts.addStringInput(Message.requestProductKey());
    amount = askForProducts.addIntegerInput(Message.requestAmount());
    addMore = askForProducts.addBooleanInput(Message.requestMore());
  }

  @Override
  public final void execute() throws DialogException {
    TreeMap<String, Integer> productsAndAmounts = new TreeMap<String, Integer>(); 
    try{
      _form.parse();
      boolean askedForSupplier = true;

      do {

        askForProducts.parse();

        if (askedForSupplier) {
          _receiver.checkIfIsValidSupplier(supplierId.value());
          askedForSupplier = !askedForSupplier;
        }
        _receiver.checkIfIsValidProduct(productId.value(), supplierId.value());
        

        int productAmount = amount.value();
        if (productsAndAmounts.containsKey(productId.value()))    
          productAmount += productsAndAmounts.get(productId.value());

        productsAndAmounts.put(productId.value(), productAmount);

      } while (addMore.value());

    } catch (SupplierKeyDoesntExistException e) {
      throw new UnknownSupplierKeyException(supplierId.value());

    } catch (SupplierCantMakeTransactionsException e) {
      throw new UnauthorizedSupplierException(supplierId.value());

    } catch (ProductKeyDoesntExistException e) {
      throw new UnknownProductKeyException(productId.value());
      
    } catch (SupplierDoesntSupplyProductException e) {
      throw new WrongSupplierException(supplierId.value(), productId.value());
    }
    
    _receiver.registerOrder(supplierId.value(), productsAndAmounts);
  }
}
