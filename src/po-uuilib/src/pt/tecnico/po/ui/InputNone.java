package pt.tecnico.po.ui;

/** Request no value, used as a separator within a Form */
public class InputNone extends Input<Void> {

  /**
   * Build an InputNone
   * 
   * @param prompt
   *          the description used in the separator
   */
  public InputNone(String prompt) {
    super(prompt, null);
  }

  /** @see pt.tecnico.po.ui.Input#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    return true;
  }

}
