package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for unknown transaction keys. */
public class UnknownTransactionKeyException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192008L;

  /** Unknown key. */
  private int _key;

  /** @param key Unknown key to report. */
  public UnknownTransactionKeyException(int key) {
    _key = key;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unknownTransactionKey(_key);
  }

}
