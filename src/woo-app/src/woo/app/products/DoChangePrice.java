package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownProductKeyException;
import woo.exceptions.ProductKeyDoesntExistException;

/**
 * Change product price.
 */
public class DoChangePrice extends Command<Storefront> {

  Input<String> productId;
  Input<Integer> newPrice;

  public DoChangePrice(Storefront receiver) {
    super(Label.CHANGE_PRICE, receiver);

    productId = _form.addStringInput(Message.requestProductKey());
    newPrice = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.changePrice(productId.value(), newPrice.value());
    
    } catch(ProductKeyDoesntExistException e) {
      throw new UnknownProductKeyException(productId.value());
    }
  }
}
