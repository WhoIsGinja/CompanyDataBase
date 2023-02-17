package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for unknown service levels. */
public class UnknownServiceLevelException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Unknown level. */
  private String _level;

  /** @param level Unknown level to report. */
  public UnknownServiceLevelException(String level) {
    _level = level;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unknownServiceLevel(_level);
  }

}
