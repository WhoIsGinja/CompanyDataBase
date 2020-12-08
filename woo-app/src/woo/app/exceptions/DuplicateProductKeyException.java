package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception thrown when a client key is duplicated. */
public class DuplicateProductKeyException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201709021324L;

  /** Product key. */
  private String _key;

  /** @param key the duplicated key */
  public DuplicateProductKeyException(String key) {
    _key = key;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.duplicateProductKey(_key);
  }

}
