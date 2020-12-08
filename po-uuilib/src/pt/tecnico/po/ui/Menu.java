package pt.tecnico.po.ui;

import static pt.tecnico.po.ui.Dialog.IO;

/**
 * Class Menu manages a set of commands.
 */
public class Menu {

  /**
   * Menu title.
   */
  private String _title;

  /**
   * Commands available from the menu.
   */
  private Command<?>[] _commands;

  /** Where to display the menu */
  private Dialog _ui;

  /**
   * Constructor.
   * 
   * @param ui
   *          interaction for the menu.
   * @param title
   *          menu title.
   * @param commands
   *          list of commands managed by the menu.
   */
  public Menu(Dialog ui, String title, Command<?>[] commands) {
    _ui = ui;
    _title = title;
    _commands = commands;
  }

  /**
   * Constructor.
   * 
   * @param title
   *          menu title.
   * @param commands
   *          list of commands managed by the menu.
   */
  public Menu(String title, Command<?>[] commands) {
    this(IO, title, commands);
  }

  /**
   * @return the menu title (package).
   */
  public String title() {
    return _title;
  }

  /**
   * @return the number of commands.
   */
  public int size() {
    return _commands.length;
  }

  /**
   * @param n
   *          the command number (not the index) in the menu
   * @return the n-th command.
   */
  public Command<?> entry(int n) {
    return _commands[n];
  }

  /**
   * @return the commands (package).
   */
  Command<?>[] entries() {
    return _commands;
  }

  /**
   * Main function: the menu interacts with the user and executes the
   * appropriate commands.
   */
  public void open() {
    _ui.menu(this);
  }

}
