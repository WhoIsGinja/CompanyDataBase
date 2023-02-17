package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for unknown client keys. */
public class UnknownClientKeyException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Unknown key. */
  private String _key;

  /** @param key Unknown key to report. */
  public UnknownClientKeyException(String key) {
    _key = key;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unknownClientKey(_key);
  }

}
