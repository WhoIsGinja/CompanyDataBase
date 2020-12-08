package pt.tecnico.po.ui;

import java.io.IOException;

/**
 * User interaction (either through the keyboard or files).
 */
public class Dialog {

  private final static String ACTION_SWING = "swing";
  private final static String ACTION_TEXT = "text";

  /** Single instance of this class. */
  public static/* final */Dialog IO = new Dialog();

  /** Interaction with subsystem (text, swing, ...). */
  private Interaction _subsystem;

  /**
   * Singleton constructor (private).
   */
  private Dialog() {
    try {
      String name = System.getProperty(PropertyNames.ACTION_CHANNEL);
      if (name == null) {
        // applets cannot do System.getProperty()
        try {
          System.in.available();
          _subsystem = new InteractionUsingText();
        } catch (IOException e) {
          _subsystem = new InteractionUsingSwing();
        }
      }
      else {
        if (name.equalsIgnoreCase(ACTION_SWING))
          _subsystem = new InteractionUsingSwing();
        else if (name.equalsIgnoreCase(ACTION_TEXT))
          _subsystem = new InteractionUsingText();
        else
          _subsystem = new InteractionUsingText();
      }
    } catch (SecurityException e) {
      System.err.println(e);
    }
  }

  /**
   * supply the action interface (package).
   * 
   * @param action
   *          interaction type
   */
  public Dialog(Interaction action) {
    _subsystem = action;
  }

  /**
   * Set the title for the main window
   * 
   * @param title
   *          the String to define as the title
   */
  public void setTitle(String title) {
    _subsystem.setTitle(title);
  }

  /**
   * Display a Menu.
   * 
   * @param menu
   *          menu to be displayed
   */
  public void menu(Menu menu) {
    _subsystem.menu(menu);
  }

  /**
   * Fill a Form.
   * 
   * @param form
   *          form to be filled
   */
  public void form(Form form) {
    _subsystem.form(form);
  }

  /**
   * Display a text message
   * 
   * @param d
   *          the String to display
   */
  public void message(Display d) {
    _subsystem.message(d);
  }

  /** Close the interaction */
  public void close() {
    _subsystem.close();
  }

}
