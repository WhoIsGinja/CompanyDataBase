package pt.tecnico.po.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Applet interaction.
 */
@SuppressWarnings("nls")
public class InteractionUsingApplet extends JApplet implements Interaction {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201609171556L;

  /**
   * Start rotine called by the internet browser. This rotines fetches the
   * <b>mainClass</B> parameter, builds the <code>main()</code> arguments from
   * the <b>mainArgs</b> parameter and calls the application's main rotine:
   * <b>mainClass.main(mainArgs)</b>. The <b>AppletInteraction</b> is then
   * called back by the <b>UserInteraction</b>.
   */
  private void main() {
    String mainClass = getParameter("mainClass");
    if (mainClass == null) {
      add(new JLabel("no 'mainClass' <param> for this Applet"));
      return;
    }
    String[] args = new String[] {};
    String mainArgs = getParameter("mainArgs");
    if (mainArgs != null)
      args = mainArgs.split(";");
    try {
      Class<?> c = Class.forName(mainClass);
      Class<?>[] cArray = new Class<?>[] { String[].class };
      Object[] oArray = new Object[] { args };
      c.getMethod("main", cArray).invoke(null, oArray);
    } catch (Exception e) {
      add(new JLabel("ERROR: " + e));
    }
  }

  /** @see java.applet.Applet#init() */
  @Override
  public void init() {
    Dialog.IO = new Dialog(this);
    try {
      // SwingUtilities.invokeLater(new Runnable() {
      Runnable r = new Runnable() {
        @Override
        public void run() {
          main();
        };
      };
      // Thread t = new Thread(r, "application.Main");
      Thread t = new Thread(r, "bank.app.App");
      t.start();
    } catch (Exception e) {
      System.err.println("main() error: " + e);
    }
    try {
      Thread.sleep(100);
    } catch (Exception e) {
      // DO NOTHING
    }
  }
  // public void start() { System.out.println("start"); } // debug
  // public void stop() { System.out.println("stop"); } // debug
  // public void destroy() { System.out.println("destroy"); } // debug

  /** @see pt.tecnico.po.ui.Interaction#close() */
  @Override
  public void close() {
    super.destroy();
  }

  /** @see pt.tecnico.po.ui.Interaction#setTitle(java.lang.String) */
  @Override
  public void setTitle(String title) {
    setName(title);
  }

  /** @see pt.tecnico.po.ui.Interaction#menu(pt.tecnico.po.ui.Menu) */
  @Override
  public void menu(Menu m) {
    int option;
    do {
      AppletPanel menu = new AppletPanel(m);
      add(menu);
      menu.await();
      option = menu.option();
      remove(menu);
      if (option == 0)
        break;
      try {
        m.entry(option - 1).perform_command();
      } catch (DialogException oi) {
        message(m.entry(option - 1).title() + ": " + oi, m.title()); //$NON-NLS-1$
      }
    } while (!m.entry(option - 1).isLast());
  }

  /** @see pt.tecnico.po.ui.Interaction#form(pt.tecnico.po.ui.Form) */
  @Override
  public void form(Form f) {
    AppletPanel form = new AppletPanel(f);
    add(form);
    do {
      form.await();
    } while (!form.parse());
    remove(form);
  }

  /** @see pt.tecnico.po.ui.Interaction#message(pt.tecnico.po.ui.Display) */
  @Override
  public void message(Display d) {
    message(d.getText(), d.getTitle());
  }

  /**
   * Display a Message.
   * 
   * @param s
   * @param title
   */
  private void message(String s, String title) {
    AppletPanel mesg = new AppletPanel(s, title);
    add(mesg);
    mesg.await();
    remove(mesg);
  }

  /**
   * The class manages the body of the window, that can be used to select an
   * option (Command) from a Menu, fill the Input values of a Form, or output a
   * text message
   */
  public class AppletPanel extends JPanel implements ActionListener {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201608221459L;

    /** The menu option selected */
    private int _opt;

    /** The OK button was pressed or a menu option selected */
    private boolean _end;

    /**
     * Association between user Input instances and Applet's JTextField values
     */
    private Map<Input<?>, JTextField> _inputs;

    /**
     * implements ActionListener is called when a menu option is selected or
     * when th OK button is pressed is Forms or messages.
     * 
     * @param evt
     *          the ActionEvent to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
      _opt = Integer.parseInt(evt.getActionCommand());
      _end = true;
      try {
        Thread.sleep(100);
      } catch (Exception e) {
        // IGNORE: DO NOTHING
      }
    }

    /**
     * Sleep the current thread so that other actions can be performed
     * 
     * @param n
     *          time to wait
     */
    private synchronized void sleep(int n) {
      try {
        Thread.sleep(n);
      } catch (InterruptedException ie) {
        System.out.println(ie);
      }
    }

    /**
     * Wait until the OK button is pressed and the <b>_end</b> becomes
     * <b>true</b>
     */
    private synchronized void await() {
      _end = false;
      while (!_end) {
        sleep(1);
      }
      // should Thread.currentThread().wait(); for notifyAll
    }

    /** @return the select option from a menu */
    private int option() {
      return _opt;
    }

    /**
     * Copy the user inputed values to the respective Input class values
     * 
     * @return parsing success
     */
    private boolean parse() {
      boolean ret = true;
      for (Input<?> in : _inputs.keySet()) {
        if (in.regex() != null)
          if (!in.parse(_inputs.get(in).getText())) {
            _inputs.get(in).setText("");
            ret = false;
          }
      }
      return ret;
    }

    /**
     * Build an AppletPanel to display a Menu
     * 
     * @param m
     *          the Menu to display
     */
    AppletPanel(Menu m) {
      super(new GridLayout(m.size() + 3, 1));
      JLabel separator = new JLabel(""); // não gosto do JSeparator

      int[] key = { KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5,
          KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9 };

      add(new JLabel(m.title(), SwingConstants.CENTER));
      add(separator);

      JButton jb;
      for (int i = 0; i < m.size(); i++)
        if (m.entry(i).isValid()) {
          add(jb = new JButton((i + 1) + " - " + m.entry(i).title()));
          jb.addActionListener(this);
          jb.setActionCommand("" + (i + 1));
          if (i < 9)
            jb.setMnemonic(key[i]);
        } else
          add(new JLabel((i + 1) + " - " + m.entry(i).title(), SwingConstants.CENTER));
      add(jb = new JButton(Messages.exit()));
      jb.addActionListener(this);
      jb.setActionCommand("0");
      jb.setMnemonic(KeyEvent.VK_0);
    }

    /**
     * Build an AppletPanel to display a Form
     * 
     * @param form
     *          the Form to display
     */
    AppletPanel(Form form) {
      super(new GridLayout(form.entries().size() + 2, 2));
      _inputs = new HashMap<Input<?>, JTextField>();
      JLabel separator = new JLabel("");

      if (form.title() != null) {
        add(separator);
        add(new JLabel(form.title()));
      }

      boolean first = true;
      for (Input<?> in : form.entries()) {
        JLabel label = new JLabel(in.prompt(), SwingConstants.TRAILING);
        add(label);
        if (in.regex() != null) {
          JTextField textField = in.cleared() ? new JTextField(10)
              : new JTextField(in.toString(), 10);
          add(textField);
          label.setLabelFor(textField);
          _inputs.put(in, textField);
          if (first) {
            first = false;
            textField.setFocusAccelerator('1');
          }
        } else {
          add(separator);
        }
      }
      add(separator);

      JButton jb = new JButton("OK");
      add(jb);
      jb.addActionListener(this);
      jb.setActionCommand("0");
      jb.setMnemonic(KeyEvent.VK_ENTER);
    }

    /**
     * Build an AppletPanel to display a Message
     * 
     * @param s
     *          the Message to display
     * @param title
     */
    AppletPanel(String s, String title) {
      // setLayout(new GridLayout(2,1));
      setLayout(new BorderLayout());

      if (title != null)
        add(new JLabel(title, SwingConstants.CENTER));

      JTextArea textArea = new JTextArea(5, 20);
      textArea.setEditable(false);
      textArea.append(s);
      // textArea.setFocusAccelerator('1'); // nao editavel => no focus

      JScrollPane scrollPane = new JScrollPane(textArea);
      add(scrollPane, BorderLayout.CENTER);

      JButton jb = new JButton("OK");
      add(jb, BorderLayout.SOUTH);
      jb.addActionListener(this);
      jb.setActionCommand("0");
      jb.setMnemonic(KeyEvent.VK_ENTER);
    }
  }

}
