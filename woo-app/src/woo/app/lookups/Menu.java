package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import woo.Storefront;

/** Lookups menu. */
public class Menu extends pt.tecnico.po.ui.Menu {

  /** @param receiver command executor */
  public Menu(Storefront receiver) {
    super(Label.TITLE, new Command<?>[] { //
        new DoLookupProductsUnderTopPrice(receiver), //
        new DoLookupPaymentsByClient(receiver), //
    });
  }

}
