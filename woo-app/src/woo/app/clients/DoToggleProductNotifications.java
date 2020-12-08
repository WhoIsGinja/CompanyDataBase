package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.UnknownClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;

import woo.exceptions.ClientKeyDoesntExistException;
import woo.exceptions.ProductKeyDoesntExistException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<Storefront> {

  Input<String> clientId;
  Input<String> productId;

  public DoToggleProductNotifications(Storefront storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    
    clientId = _form.addStringInput(Message.requestClientKey());
    productId = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public void execute() throws DialogException {
    try {
      _form.parse();

      boolean notificationOn = _receiver.toggleProductNotifications(clientId.value(), productId.value());

      if( notificationOn ) {
        _display.popup(Message.notificationsOn(clientId.value(), productId.value()));
      } else {
        _display.popup(Message.notificationsOff(clientId.value(), productId.value()));
      }

    } catch (ClientKeyDoesntExistException e) {
      throw new UnknownClientKeyException(clientId.value());
    
    } catch (ProductKeyDoesntExistException e) {
      throw new UnknownProductKeyException(productId.value());
    }
  }

}
