package woo.app.main;

import pt.tecnico.po.ui.Command;

import java.io.IOException;
import java.io.FileNotFoundException;
import woo.exceptions.MissingFileAssociationException;                                                                                                            import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.FileOpenFailedException;


/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Storefront> {

  Input<String> inputFileName;
  String fileName;

  /** @param receiver */
  public DoSave(Storefront receiver) {
    super(Label.SAVE, receiver);
    inputFileName = _form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {

    try {
      if (_receiver.getFileName().equals("")) {
        _form.parse();

        // Initialize file name
        _receiver.saveAs(inputFileName.value());  
      } else {
         // File name already initialized
        _receiver.save();
      }
    } catch (MissingFileAssociationException | IOException e) {
      throw new FileOpenFailedException(_receiver.getFileName());
    }
  }
}
