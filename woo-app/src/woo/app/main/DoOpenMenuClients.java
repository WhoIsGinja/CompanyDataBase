package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import woo.Storefront;

/**
 * Open menu for client management.
 */
public class DoOpenMenuClients extends Command<Storefront> {

  public DoOpenMenuClients(Storefront receiver) {
    super(Label.OPEM_MENU_CLIENTS, receiver);
  }

  @Override
  public final void execute() {
    Menu menu = new woo.app.clients.Menu(_receiver);
    menu.open();
  }

}
