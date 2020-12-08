package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                        import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import java.util.Collection;

import woo.Client;

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<Storefront> {

  public DoShowAllClients(Storefront storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    boolean showNotifications = false;

    Collection<Client> clients = _receiver.requestAllClients();
    for (Client client : clients) {
      _display.addLine(client.toString(showNotifications));
    }

    _display.display();
  }
}
