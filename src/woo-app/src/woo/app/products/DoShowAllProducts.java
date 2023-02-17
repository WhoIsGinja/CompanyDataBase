package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import java.util.Collection;

import woo.Product;

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<Storefront> {

  public DoShowAllProducts(Storefront receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    Collection<Product> products = _receiver.requestAllProducts();
    for (Product product : products) {
      _display.addLine(product.toString());
    }

    _display.display();
  }

}
