package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for reporting general problems opening and processing files. */
public class FileOpenFailedException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Filename. */
  String _filename;

  /** @param filename Problematic filename to report. */
  public FileOpenFailedException(String filename) {
    _filename = filename;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.problemOpeningFile(_filename);
  }

}
