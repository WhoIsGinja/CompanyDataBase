package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import woo.Storefront;

/**
 * Open menu for lookups.
 */
public class DoOpenMenuLookups extends Command<Storefront> {

  public DoOpenMenuLookups(Storefront receiver) {
    super(Label.OPEM_MENU_LOOKUPS, receiver);
  }

  @Override
  public final void execute() {
    Menu menu = new woo.app.lookups.Menu(_receiver);
    menu.open();
  }

}
