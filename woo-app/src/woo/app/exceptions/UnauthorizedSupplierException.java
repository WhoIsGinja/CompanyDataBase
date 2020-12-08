package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for reporting unauthorized supplier attempts. */
public class UnauthorizedSupplierException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009200054L;

  /** Unknown key. */
  private String _key;

  /** @param key unauthorized key to report. */
  public UnauthorizedSupplierException(String key) {
    _key = key;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unauthorizedSupplier(_key);
  }

}
