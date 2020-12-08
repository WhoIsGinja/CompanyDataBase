package pt.tecnico.po.ui;

/**
 * Request an Input value in a Form
 * 
 * @param <Type>
 *          type to be read
 */
public abstract class Input<Type> {

  /** the description used in the request */
  private String _prompt;

  /** regular expression to match */
  private String _regex;

  /** a new value was read */
  private boolean _clear;

  /** value to be read */
  protected Type _value;

  /** @return the description of the request */
  public String prompt() {
    return _prompt;
  }

  /**
   * Build an Input without a title
   */
  protected Input() {
    this(null, null);
  }

  /**
   * Build an Input with a title
   * 
   * @param prompt
   *          the description of the requested value
   * @param regex
   *          the regular expression to be matched
   */
  protected Input(String prompt, String regex) {
    _prompt = prompt;
    _regex = regex;
  }

  /**
   * Set the value
   * 
   * @param value
   */
  public void set(Type value) {
    _value = value;
    dirty();
  }

  /** @return the value */
  public Type value() {
    return _value;
  }

  /** Mark the Input as unread */
  public void clear() {
    _clear = true;
  }

  /** Mark the Input as read */
  protected void dirty() {
    _clear = false;
  }

  /** @return true if the Input is cleared */
  public boolean cleared() {
    return _clear;
  }

  /**
   * Each Input must parse its own specific type of value, written in accordance
   * with the specific regular expression.
   * 
   * @param in
   *          the string to be parsed
   * @return the status of parsing success
   */
  public abstract boolean parse(String in);

  /**
   * The regular expression used to validate the Input
   * 
   * @return the regular expression to match
   */
  public String regex() {
    return _regex;
  }

  /** @see java.lang.Object#toString() */
  @Override
  public String toString() {
    return "" + _value; //$NON-NLS-1$
  }

}
