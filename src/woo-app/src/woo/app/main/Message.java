package woo.app.main;

/** Messages for interaction. */
public class Message {

  /** @return string with prompt for filename to open. */
  public static final String openFile() {
    return "Ficheiro a abrir: ";
  }

  /** @return string with a warning and a question. */
  public static final String newSaveAs() {
    return "Ficheiro sem nome. " + saveAs();
  }

  /** @return string asking for a filename. */
  public static final String saveAs() {
    return "Guardar ficheiro como: ";
  }

  /** @return string confirming that user wants to save. */
  public static final String saveBeforeExit() {
    return "Guardar antes de fechar? ";
  }

  /** @return string showing current date. */
  public static final String currentDate(int date) {
    return "Data actual: " + date;
  }

  /** @return string prompting for the number of days to advance (integer). */
  public static final String requestDaysToAdvance() {
    return "Número de dias a avançar: ";
  }

  /**
   * @param available available balance
   * @param accounting accounting balance 
   * @return string describing balance.
   */
  public static final String currentBalance(int available, int accounting) {
    return "Saldo disponível: " + available + "\n" + "Saldo contabilístico: " + accounting;
  }

}