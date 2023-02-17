package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import woo.Storefront;

/**
 * Open menu for supplier management.
 */
public class DoOpenMenuSuppliers extends Command<Storefront> {

  public DoOpenMenuSuppliers(Storefront receiver) {
    super(Label.OPEM_MENU_SUPPLIERS, receiver);
  }

  @Override
  public final void execute() {
    Menu menu = new woo.app.suppliers.Menu(_receiver);
    menu.open();
  }

}
