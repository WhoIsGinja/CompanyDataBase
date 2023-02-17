package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.SupplierKeyDoesntExistException;

import woo.Transaction;
import woo.Order;

import java.util.Collection;

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<Storefront> {

  Input<String> supplierId;

  public DoShowSupplierTransactions(Storefront receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    
    supplierId = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {

      Collection<Order> orders = _receiver.requestSupplierTransactions(supplierId.value());

      for(Order order : orders) {
        _display.addLine(order.toString());
      }

      _display.display();

    } catch (SupplierKeyDoesntExistException e) {
      throw new UnknownSupplierKeyException(supplierId.value());
    }

  }

}
