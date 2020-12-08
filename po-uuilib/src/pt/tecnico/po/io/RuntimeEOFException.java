package pt.tecnico.po.io;

/**
 * Class RuntimeEOFException
 */
public class RuntimeEOFException extends RuntimeException {

  /** Serial number for serialization. */
  static final long serialVersionUID = 200610291655L;

  /** Default constructor. */
  public RuntimeEOFException() {
    // intentionally left empty
  }

  /**
   * Constructor.
   * 
   * @param msg
   *          message describing the exception.
   */
  public RuntimeEOFException(String msg) {
    super(msg);
  }

}