package woo.app.main;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Show global balance.
 */
public class DoShowGlobalBalance extends Command<Storefront> {


  public DoShowGlobalBalance(Storefront receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() {
    int availableBalance = _receiver.requestAvailableBalance();
    int accountingBalance = _receiver.requestAccountingBalance();

    _display.popup(Message.currentBalance(availableBalance, accountingBalance));   
  }
}
