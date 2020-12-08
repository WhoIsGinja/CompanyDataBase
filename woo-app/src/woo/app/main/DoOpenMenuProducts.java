package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Menu;
import woo.Storefront;

/**
 * Open menu for product management.
 */
public class DoOpenMenuProducts extends Command<Storefront> {

  public DoOpenMenuProducts(Storefront receiver) {
    super(Label.OPEM_MENU_PRODUCTS, receiver);
  }

  @Override
  public final void execute() {
    Menu menu = new woo.app.products.Menu(_receiver);
    menu.open();
  }

}
