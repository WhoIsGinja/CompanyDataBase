package woo.app.lookups;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.UnknownKeyException;

import java.util.Collection;
import woo.Sale;

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<Storefront> {

  Input<String> clientId;

  public DoLookupPaymentsByClient(Storefront storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    
    clientId = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    try { 
      _form.parse();

      Collection<Sale> transactionsPayed = _receiver.requestClientPayments(clientId.value());

      for(Sale sale : transactionsPayed) {
        _display.addLine(sale.toString());
     }

      _display.display();
    
    } catch (UnknownKeyException e) {
      throw new UnknownClientKeyException(clientId.value());
    }
  }

}
