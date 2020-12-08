package pt.tecnico.po.ui;

/** Request a String within a Form */
class InputString extends Input<String> {

  /**
   * Build an InputString
   * 
   * @param prompt
   *          the description used in the request
   */
  public InputString(String prompt) {
    super(prompt, Constants.REGEX_STRING);
  }

  /**
   * @see pt.tecnico.po.ui.Input#parse(java.lang.String)
   */
  @Override
  public boolean parse(String in) {
    set(in);
    return _value.matches(regex());
  }

}
