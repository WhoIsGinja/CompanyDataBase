package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import woo.Storefront;

/**
 * Open menu for transaction management.
 */
public class DoOpenMenuTransactions extends Command<Storefront> {

  public DoOpenMenuTransactions(Storefront receiver) {
    super(Label.OPEM_MENU_TRANSACTIONS, receiver);
  }

  @Override
  public final void execute() {
    Menu menu = new woo.app.transactions.Menu(_receiver);
    menu.open();
  }

}
