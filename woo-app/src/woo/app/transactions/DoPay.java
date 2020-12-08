package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownTransactionKeyException;

import woo.exceptions.UnknownKeyException;
/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<Storefront> {

  Input<Integer> transactionId;

  public DoPay(Storefront storefront) {
    super(Label.PAY, storefront);
    
    transactionId = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.payTransaction(transactionId.value());
      
    } catch (UnknownKeyException e) {
      throw new UnknownTransactionKeyException(transactionId.value());
    }
  }

}
