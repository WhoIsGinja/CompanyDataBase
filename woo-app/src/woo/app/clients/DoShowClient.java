package woo.app.clients;

import pt.tecnico.po.ui.Command;
import woo.exceptions.UnknownKeyException;
import woo.app.exceptions.UnknownClientKeyException;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

/**
 * Show client.
 */
public class DoShowClient extends Command<Storefront> {

  Input<String> id;

  public DoShowClient(Storefront storefront) {
    super(Label.SHOW_CLIENT, storefront);
    id = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      boolean showNotifications = true; 
      
      _display.popup(_receiver.clientToString(id.value(), showNotifications));
  } catch (UnknownKeyException e) {
      throw new UnknownClientKeyException(id.value());
    }
  }

}
