package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception thrown when a supplier key is duplicated. */
public class DuplicateSupplierKeyException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201709021324L;

  /** Supplier key. */
  private String _key;

  /** @param key the duplicated key */
  public DuplicateSupplierKeyException(String key) {
    _key = key;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.duplicateSupplierKey(_key);
  }

}
