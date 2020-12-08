package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;

import woo.exceptions.DuplicateKeyException;
import woo.exceptions.SupplierKeyDoesntExistException;

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<Storefront> {

  Input<String> productId;
  Input<String> title;
  Input<String> author;
  Input<String> ISBN;
  Input<Integer> price;
  Input<Integer> criticalValue;
  Input<String> supplierId;  

  public DoRegisterProductBook(Storefront receiver) {
    super(Label.REGISTER_BOOK, receiver);

    productId = _form.addStringInput(Message.requestProductKey());
    title = _form.addStringInput(Message.requestBookTitle());
    author = _form.addStringInput(Message.requestBookAuthor());
    ISBN = _form.addStringInput(Message.requestISBN());
    price = _form.addIntegerInput(Message.requestPrice());
    criticalValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    supplierId = _form.addStringInput(Message.requestSupplierKey());

  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {

      _receiver.registerProductBook(productId.value(), title.value(), author.value(), ISBN.value(), supplierId.value(), price.value(), criticalValue.value());
    
    } catch (DuplicateKeyException e) {
      throw new DuplicateProductKeyException(productId.value());
      
    } catch (SupplierKeyDoesntExistException e) {
      throw new UnknownSupplierKeyException(supplierId.value());
    
    }
  }
}
