package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.ClientKeyDoesntExistException;

import woo.Transaction;
import woo.Sale;

import java.util.Collection;

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<Storefront> {

  Input<String> clientId;

  public DoShowClientTransactions(Storefront storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    clientId = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {

      Collection<Sale> sales = _receiver.requestClientTransactions(clientId.value());

      for(Sale sale : sales) {
        _display.addLine(sale.toString());
      }

      _display.display();

    } catch (ClientKeyDoesntExistException e) {
      throw new UnknownClientKeyException(clientId.value());
    }
  }

}
