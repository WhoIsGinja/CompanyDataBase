package pt.tecnico.po.ui;

/** Property names. */
@SuppressWarnings("nls")
final class Constants {

  /** Application title (e.g., for window titles) */
  public final static String MAIN_TITLE = "Programação com Objectos";

  /** How do you say "yes"? */
  public final static String BOOLEAN_WORD_YES = "sim";

  /** How do you say "yes"? */
  public final static String BOOLEAN_WORD_NO = "nao";

  /** How do you say "yes"? */
  public final static char BOOLEAN_CHAR_YES = 's';

  /** How do you say "yes"? */
  public final static char BOOLEAN_CHAR_NO = 'n';

  /** Property name: input property name. */
  public final static String REGEX_BOOLEAN = "[ns]";

  /** Property name: input property name. */
  public final static String REGEX_NUMBER_INTEGER = "[0-9]+";

  /** Property name: input property name. */
  public final static String REGEX_NUMBER_REAL = "[0-9]+\\.[0-9]+[eE][+-][0-9]+";

  /** Property name: input property name. */
  public final static String REGEX_STRING = ".*";

  /** Prevent instantiantion. */
  private Constants() {
  }

}