package pt.tecnico.po.ui;

/** Request a float within a Form */
class InputFloat extends Input<Float> {

  /**
   * Build an InputFloat
   * 
   * @param prompt
   *          the description used in the request
   */
  public InputFloat(String prompt) {
    super(prompt, Constants.REGEX_NUMBER_REAL);
  }

  /** @see pt.tecnico.po.ui.Input#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    try {
      set(Float.parseFloat(in));
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
