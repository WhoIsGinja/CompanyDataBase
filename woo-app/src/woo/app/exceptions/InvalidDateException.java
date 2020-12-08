package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for date-related problems. */
public class InvalidDateException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192335L;

  /** Bad date. */
  private int _date;

  /** @param date bad date to report. */
  public InvalidDateException(int date) {
    _date = date;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.invalidDate(_date);
  }

}
