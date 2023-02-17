package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Storefront;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.lang.ClassNotFoundException;
import woo.exceptions.UnavailableFileException;
import woo.app.exceptions.FileOpenFailedException; 

//FIXME import other classes

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<Storefront> {

  Input<String> fileName;

  /** @param receiver */
  public DoOpen(Storefront receiver) {
    super(Label.OPEN, receiver);
    fileName = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.load(fileName.value());
    } catch (UnavailableFileException | ClassNotFoundException | IOException e) {
      throw new FileOpenFailedException(fileName.value());
    }
  }

}
