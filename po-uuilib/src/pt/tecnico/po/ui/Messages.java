/**
 * Messages for internal use. Thus none of the methods is public.
 */
package pt.tecnico.po.ui;

/**
 * Messages.
 */
@SuppressWarnings("nls")
public final class Messages {

	/**
	 * Message for presenting errors in commands.
	 * 
	 * @param error
	 *            error message.
	 * @return message text.
	 */
	public static final String invalidOperation(String error) {
		return "Operação inválida: " + error;
	}

  /**
   * Prompt for menu option.
   * 
   * @return prompt text.
   */
	public static final String selectAnOption() {
    return "Escolha uma opção: ";
  }

  /**
   * Massage for communicating an invalid option.
   * 
   * @return message text.
   */
  public static final String invalidOption() {
    return "Opção inválida!";
  }

  /**
   * Exit option for all menus.
   * 
   * @return message text.
   */
  public static final String exit() {
    return "0 - Sair";
  }

  /** Constructor. */
  private Messages() {
    // intentionally left empty
  }

}
