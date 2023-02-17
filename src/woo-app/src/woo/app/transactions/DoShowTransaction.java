package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownTransactionKeyException;
import woo.exceptions.UnknownKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<Storefront> {

  Input<Integer> transactionId;

  public DoShowTransaction(Storefront receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    
    transactionId = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {

      String transaction = _receiver.transactionToString(transactionId.value());
      _display.popup(transaction);

    
    } catch (UnknownKeyException e) {
      throw new UnknownTransactionKeyException(transactionId.value());
    }
  }

}
