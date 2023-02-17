package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.UnavailableProductException;

import woo.exceptions.ClientKeyDoesntExistException;
import woo.exceptions.ProductKeyDoesntExistException;
import woo.exceptions.MoreAmountThanStockException;

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<Storefront> {

  Input<String> clientId;
  Input<Integer> deadLine;
  Input<String> productId;
  Input<Integer> amount;

  public DoRegisterSaleTransaction(Storefront receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    clientId = _form.addStringInput(Message.requestClientKey());
    deadLine = _form.addIntegerInput(Message.requestPaymentDeadline());
    productId = _form.addStringInput(Message.requestProductKey());
    amount = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException {
    try {
      _form.parse();
      _receiver.registerSale(clientId.value(), deadLine.value(), productId.value(), amount.value()); 
    
    } catch (ClientKeyDoesntExistException e) {
      throw new UnknownClientKeyException(clientId.value());
    } catch (ProductKeyDoesntExistException e) {
      throw new UnknownProductKeyException(productId.value());
    } catch (MoreAmountThanStockException e) {
      int productStock = _receiver.getProductStock(productId.value());
      throw new UnavailableProductException(productId.value(), amount.value(), productStock);
    }
  }
}
