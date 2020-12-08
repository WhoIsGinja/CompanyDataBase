package pt.tecnico.po.ui;

/** Request a boolean within a Form */
class InputBoolean extends Input<Boolean> {

  /**
   * Build an InputBoolean
   * 
   * @param prompt
   *          the description used in the request
   */
  public InputBoolean(String prompt) {
    super(prompt, Constants.REGEX_BOOLEAN);
  }

  /** @see pt.tecnico.po.ui.Input#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    if (in.length() == 1 && (in.charAt(0) == Constants.BOOLEAN_CHAR_YES
        || in.charAt(0) == Constants.BOOLEAN_CHAR_NO)) {
      _value = in.charAt(0) == Constants.BOOLEAN_CHAR_YES;
      dirty();
      return true;
    }
    return false;
  }

  /** @see pt.tecnico.po.ui.Input#toString() */
  @Override
  public String toString() {
    return value() ? Constants.BOOLEAN_WORD_YES : Constants.BOOLEAN_WORD_NO;
  }

}
