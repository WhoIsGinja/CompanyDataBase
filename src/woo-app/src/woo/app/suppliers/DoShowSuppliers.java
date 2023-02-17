package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.suppliers.Message;
import java.util.Collection;

import woo.Supplier;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<Storefront> {

  public DoShowSuppliers(Storefront receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    Collection<Supplier> suppliers = _receiver.requestAllSuppliers();
    for (Supplier supplier : suppliers) {
      _display.addLine(supplier.toString(Message.yes(), Message.no()));
    }

    _display.display();
  }
}
