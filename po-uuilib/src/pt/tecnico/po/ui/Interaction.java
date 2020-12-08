package pt.tecnico.po.ui;

/**
 * Generic interaction (interface).
 */
public interface Interaction {

  /**
   * Display a Menu.
   * 
   * @param menu
   *          menu to be displayed
   */
  public void menu(Menu menu);

  /**
   * Fill a Form.
   * 
   * @param form
   *          form to be filled
   */
  public void form(Form form);

  /**
   * Display a Message.
   * 
   * @param display
   *          what to display
   */
  public void message(Display display);

  /**
   * Set the Interaction title.
   * 
   * @param title
   *          the interaction's title
   */
  public void setTitle(String title);

  /**
   * Close all I/O channels.
   */
  public void close();
  
}
