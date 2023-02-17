package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for unknown service types. */
public class UnknownServiceTypeException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Unknown type. */
  private String _type;

  /** @param type Unknown type to report. */
  public UnknownServiceTypeException(String type) {
    _type = type;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unknownServiceType(_type);
  }

}
