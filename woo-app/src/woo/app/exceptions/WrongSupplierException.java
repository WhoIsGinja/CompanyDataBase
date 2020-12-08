package woo.app.exceptions;

import pt.tecnico.po.ui.DialogException;

/** Exception for reporting wrong supplier/product associations. */
public class WrongSupplierException extends DialogException {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009200054L;

  /** Supplier key. */
  private String _skey;

  /** Product key. */
  private String _pkey;

  /** 
   * @param skey supplier key.
   * @param pkey product key. 
   */
  public WrongSupplierException(String skey, String pkey) {
    _skey = skey;
    _pkey = pkey;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.wrongSupplier(_skey, _pkey);
  }

}
