package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import woo.Storefront;

/** Suppliers menu. */
public class Menu extends pt.tecnico.po.ui.Menu {

  /** @param receiver command executor */
  public Menu(Storefront receiver) {
    super(Label.TITLE, new Command<?>[] { //
        new DoShowSuppliers(receiver), //
        new DoRegisterSupplier(receiver), //
        new DoToggleTransactions(receiver), //
        new DoShowSupplierTransactions(receiver), //
    });
  }

}
