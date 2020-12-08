package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.SupplierKeyDoesntExistException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<Storefront> {

  Input<String> supplierId;

  public DoToggleTransactions(Storefront receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    
    supplierId = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      
      boolean toggled = _receiver.toggleTransactions(supplierId.value());

      if (toggled)
        _display.popup(Message.transactionsOn(supplierId.value()));
      else 
        _display.popup(Message.transactionsOff(supplierId.value()));
    
    } catch (SupplierKeyDoesntExistException e) {
      throw new UnknownSupplierKeyException(supplierId.value());

    }
  }

}
