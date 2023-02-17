package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.DuplicateSupplierKeyException;
import woo.exceptions.DuplicateKeyException;

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<Storefront> {

  Input<String> supplierId;
  Input<String> supplierName;
  Input<String> supplierAddress;

  public DoRegisterSupplier(Storefront receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    
    supplierId = _form.addStringInput(Message.requestSupplierKey());
    supplierName = _form.addStringInput(Message.requestSupplierName());
    supplierAddress = _form.addStringInput(Message.requestSupplierAddress());
    
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {

      _receiver.registerSupplier(supplierId.value(), supplierName.value(), supplierAddress.value());
    
    } catch (DuplicateKeyException e) {
      throw new DuplicateSupplierKeyException(supplierId.value());
    }
  }

}
