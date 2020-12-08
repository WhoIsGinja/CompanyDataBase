package woo.app.lookups;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import java.util.Collection;

import woo.Product;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductsUnderTopPrice extends Command<Storefront> {

  Input<Integer> topPrice;

  public DoLookupProductsUnderTopPrice(Storefront storefront) {
    super(Label.PRODUCTS_UNDER_PRICE, storefront);
    
    topPrice = _form.addIntegerInput(Message.requestPriceLimit());
  }

  @Override
  public void execute() throws DialogException {

    _form.parse();

    Collection<Product> products = _receiver.requestProductsUnder(topPrice.value());
    for (Product product : products) {
      _display.addLine(product.toString());
    }

    _display.display();
  }
}
