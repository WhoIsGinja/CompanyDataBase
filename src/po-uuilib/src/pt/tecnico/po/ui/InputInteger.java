package pt.tecnico.po.ui;

/** Request an integer within a Form */
class InputInteger extends Input<Integer> {

  /**
   * Build an InputInteger
   * 
   * @param prompt
   *          the description used in the request
   */
  public InputInteger(String prompt) {
    super(prompt, Constants.REGEX_NUMBER_INTEGER);
  }

  /** @see pt.tecnico.po.ui.Input#parse(java.lang.String) */
  @Override
  public boolean parse(String in) {
    try {
      set(Integer.parseInt(in));
    } catch (NumberFormatException e) {
      // println(Messages.invalidNumber(e));
      return false;
    }
    return true;
  }

}
