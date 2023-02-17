package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception thrown when a client key is duplicated. */
public class UnavailableProductException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201709021324L;

  /** Product key. */
  private String _key;

  /** Requested amount. */
  int _requested;
  
  /** Available amount. */
  int _available;
  
  /** 
   * @param key the requested key
   * @param requested
   * @param available
   */
  public UnavailableProductException(String key, int requested, int available) {
    _key = key;
    _requested = requested;
    _available = available;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.unavailable(_key, _requested, _available);
  }

}
