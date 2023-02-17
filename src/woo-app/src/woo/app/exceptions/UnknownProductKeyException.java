package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for unknown product keys. */
public class UnknownProductKeyException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Unknown key. */
  String _key;

  /** @param key Unknown key to report. */
  public UnknownProductKeyException(String key) {
    _key = key;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unknownProductKey(_key);
  }

}
