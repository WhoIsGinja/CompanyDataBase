package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for unknown supplier keys. */
public class UnknownSupplierKeyException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009200054L;

  /** Unknown key. */
  private String _key;

  /** @param key Unknown key to report. */
  public UnknownSupplierKeyException(String id) {
    _key = id;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unknownSupplierKey(_key);
  }

}
