package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.DuplicateClientKeyException;
import woo.exceptions.DuplicateKeyException;

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<Storefront> {

  Input<String> id;
  Input<String> name;
  Input<String> address; 

  public DoRegisterClient(Storefront storefront) {
    super(Label.REGISTER_CLIENT, storefront);

    id = _form.addStringInput(Message.requestClientKey());
    name = _form.addStringInput(Message.requestClientName());
    address = _form.addStringInput(Message.requestClientAddress());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.registerClient(id.value(), name.value(), address.value());
    } catch (DuplicateKeyException e) {
      throw new DuplicateClientKeyException(id.value());
    }
  }

}
